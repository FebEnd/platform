package com.platform.parent.util;

import com.platform.parent.config.ManagerShareConfig;
import com.platform.parent.config.TeacherShareConfig;
import com.platform.parent.mybatis.bean.Invoice;
import com.platform.parent.mybatis.bean.Segment;
import com.platform.parent.mybatis.bean.Statement;
import com.platform.parent.mybatis.bean.Teacher;
import com.platform.parent.mybatis.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

public class FinancialUtil {
    private static final Logger logger = LoggerFactory.getLogger(FinancialUtil.class);

    public final static int ROLE_MANAGER = 1;
    public final static int ROLE_TEACHER = 2;

    public static List<Invoice> processStatements(List<Statement> statements, TeacherShareConfig teacherShareConfig,
                                                  TeacherService teacherService, ManagerShareConfig managerShareConfig)
    {
        // get camp to statements map
        Map<Long, List<Statement>> campStatements = getCampStatements(statements);

        List<Invoice> result = new ArrayList<Invoice>();

        // account each camp
        for(Map.Entry<Long, List<Statement>> campStatement: campStatements.entrySet())
        {
            logger.info("accounting camp " + campStatement.getKey());
            // get segment to statements map
            Map<Segment, List<Statement>> segmentToStatementMap = getSegmentStatements(campStatement.getValue());

            // resolve statement segment overlaps
            resolveStatementOverlaps(segmentToStatementMap);

            // account based on the split segments
            result.addAll(accountStatements(segmentToStatementMap, teacherShareConfig, teacherService, managerShareConfig));
        }

        return result;
    }

    private static Map<Long, List<Statement>> getCampStatements(List<Statement> statements)
    {
        Map<Long, List<Statement>> campStatements = new TreeMap<Long, List<Statement>>();
        for(Statement statement: statements)
        {
            long campId = statement.getCampId();
            if (campStatements.containsKey(campId))
            {
                campStatements.get(campId).add(statement);
            }
            else
            {
                List<Statement> statementList = new ArrayList<Statement>();
                statementList.add(statement);
                campStatements.put(campId, statementList);
            }
        }

        return campStatements;
    }

    private static Map<Segment, List<Statement>> getSegmentStatements(List<Statement> statements)
    {
        Map<Segment, List<Statement>> segmentToStatementMap = new TreeMap<Segment, List<Statement>>();
        for(Statement statement: statements)
        {
            Segment segment = statement.getSegment();
            if (segmentToStatementMap.containsKey(segment))
            {
                segmentToStatementMap.get(segment).add(statement);
            }
            else
            {
                List<Statement> statementList = new ArrayList<Statement>();
                statementList.add(statement);
                segmentToStatementMap.put(statement.getSegment(), statementList);
            }
        }

        return segmentToStatementMap;
    }

    private static List<Invoice> accountStatements(Map<Segment, List<Statement>> segmentToStatementMap,
                                                   TeacherShareConfig teacherShareConfig,
                                                   TeacherService teacherService, ManagerShareConfig managerShareConfig)
    {
        Map<Statement, List<Segment>> statementToSegmentMap = new TreeMap<Statement, List<Segment>>();
        List<Invoice> accountedInvoices = new ArrayList<Invoice>();
        for (Map.Entry<Segment, List<Statement>> entry: segmentToStatementMap.entrySet())
        {
            Segment segment = entry.getKey();
            List<Statement> statements = entry.getValue();
            int count = statements.size();
            long days = segment.days();

            accountedInvoices.addAll(accountStatements(segment, statements, teacherShareConfig, teacherService, managerShareConfig));
        }

        Map<Long, Invoice> result = new HashMap<Long, Invoice>();
        for(Invoice invoice: accountedInvoices)
        {
            long id = invoice.getId();
            if (result.containsKey(id))
            {
                Invoice resultInvoice = result.get(id);
                BigDecimal income = resultInvoice.getSettlementAmount();
                BigDecimal addedValue = income.add(invoice.getSettlementAmount());
                resultInvoice.setSettlementAmount(addedValue);
                resultInvoice.getSegments().addAll(invoice.getSegments());
            }
            else
            {
                result.put(id, invoice);
            }
        }

        for (Map.Entry<Long, Invoice> entry: result.entrySet())
        {
            Invoice invoice = entry.getValue();
            mergeSegments(invoice.getSegments());
        }

        return new ArrayList<Invoice>(result.values());
    }

    private static void mergeSegments(List<Segment> segments)
    {
        boolean merged = mergeCandidates(segments);
        while (merged) {
            merged = mergeCandidates(segments);
        }
    }

    private static void resolveStatementOverlaps(Map<Segment, List<Statement>> segmentToStatementMap)
    {
        List<Segment> overlaps = findOverlaps(segmentToStatementMap.keySet());
        while (overlaps != null)
        {
            Segment s1 = overlaps.get(0);
            Segment s2 = overlaps.get(1);
            resolveOverlaps(segmentToStatementMap, s1, s2);
            overlaps = findOverlaps(segmentToStatementMap.keySet());
        }
    }

    private static void resolveOverlaps(Map<Segment, List<Statement>> segmentToStatementMap, Segment s1, Segment s2)
    {
        List<Timestamp> overlaps = getOverlaps(s1, s2);
        if (overlaps == null)
        {
            return;
        }

        if (overlaps.size() == 2)
        {
            // segment1 and segment2 are fully overlapped, merge segment1 and segmetns2's statements
            Segment segment = new Segment(overlaps.get(0), overlaps.get(1));
            List<Statement> statements1 = segmentToStatementMap.get(s1);
            List<Statement> statements2 = segmentToStatementMap.get(s2);
            statements1.addAll(statements2);
            // s1 and s2 are equal, update instead of remove
            segmentToStatementMap.get(segment).clear();
            segmentToStatementMap.get(segment).addAll(statements1);
        }
        else if (overlaps.size() == 3)
        {
            // s1 contains s2, or s2 contains s1, merge the segments in this case
            Segment segment1 = new Segment(overlaps.get(0), overlaps.get(1));
            mergeSegments(segmentToStatementMap, s1, s2, segment1);

            Segment segment2 = new Segment(overlaps.get(1), overlaps.get(2));
            mergeSegments(segmentToStatementMap, s1, s2, segment2);

            if (!s1.equals(segment1))
            {
                segmentToStatementMap.remove(s1);
            }
            else if (!s2.equals(segment1))
            {
                segmentToStatementMap.remove(s2);
            }

            if (!s1.equals(segment2))
            {
                segmentToStatementMap.remove(s1);
            }
            else if (!s2.equals(segment2))
            {
                segmentToStatementMap.remove(s2);
            }
        }
        else if (overlaps.size() == 4)
        {
            Segment segment1 = new Segment(overlaps.get(0), overlaps.get(1));
            mergeSegments(segmentToStatementMap, s1, s2, segment1);

            Segment segment2 = new Segment(overlaps.get(1), overlaps.get(2));
            mergeSegments(segmentToStatementMap, s1, s2, segment2);

            Segment segment3 = new Segment(overlaps.get(2), overlaps.get(3));
            mergeSegments(segmentToStatementMap, s1, s2, segment3);

            segmentToStatementMap.remove(s1);
            segmentToStatementMap.remove(s2);
        }
    }

    private static List<Timestamp> getOverlaps(Segment segment1, Segment segment2) {
        if (segment1 == null || segment2 == null) {
            logger.info("No overlaps for null segments");
            return null;
        }

        if (!segment1.hasOverlap(segment2)) {
            logger.info("No overlaps for segments: [" + segment1 + "] and statement: [" + segment2 + "].");
            return null;
        }

        Set<Timestamp> overlapSet = new TreeSet<Timestamp>();
        overlapSet.add(segment1.getStart());
        overlapSet.add(segment1.getEnd());
        overlapSet.add(segment2.getStart());
        overlapSet.add(segment2.getEnd());

        List<Timestamp> overlaps = new ArrayList<Timestamp>(overlapSet);
        logger.info(overlaps.toString());

        return overlaps;
    }

    private static List<Segment> findOverlaps(Set<Segment> segments)
    {
        int i = 0;
        for (Segment segment1: segments)
        {
            int j = 0;
            for (Segment segment2: segments)
            {
                if (i != j && segment1.hasOverlap(segment2))
                {
                    return Arrays.asList(new Segment[]{segment1, segment2});
                }
                j++;
            }
            i++;
        }

        return null;
    }

    private static boolean mergeCandidates(List<Segment> segments)
    {
        int i = 0;
        for (Segment segment1: segments)
        {
            int j = 0;
            for (Segment segment2: segments)
            {
                if (i != j)
                {
                    Set<Timestamp> tsSet = new TreeSet<Timestamp>();
                    tsSet.add(segment1.getStart());
                    tsSet.add(segment1.getEnd());
                    tsSet.add(segment2.getStart());
                    tsSet.add(segment2.getEnd());

                    if (tsSet.size() == 3)
                    {
                        Timestamp[] tsArray = tsSet.toArray(new Timestamp[]{});
                        Segment merged = new Segment(tsArray[0], tsArray[2]);
                        segments.add(merged);
                        segments.remove(segment1);
                        segments.remove(segment2);
                        return true;
                    }
                }
                j++;
            }
            i++;
        }

        return false;
    }

    private static void mergeSegments(Map<Segment, List<Statement>> segmentToStatementMap, Segment originalSegment1, Segment originalSegment2, Segment newSegment)
    {
        List<Statement> statements1 = segmentToStatementMap.get(originalSegment1);
        List<Statement> statements2 = segmentToStatementMap.get(originalSegment2);
        if (originalSegment1.contains(newSegment) && originalSegment2.contains(newSegment))
        {
            // both originalSegment1 and originalSegment2 contains newSegment
            // add originalSegment1 and originalSegment2's statements to newSegment
            List<Statement> tmpStatements = new ArrayList<Statement>(statements1);
            tmpStatements.addAll(statements2);
            if (originalSegment1.equals(newSegment) || originalSegment2.equals(newSegment))
            {
                // one of the original segment is equal to newly splitted segment, update instead of remove
                segmentToStatementMap.get(newSegment).clear();
                segmentToStatementMap.get(newSegment).addAll(tmpStatements);
            }
            else
            {
                segmentToStatementMap.put(newSegment, tmpStatements);
            }
        }
        else if (originalSegment1.contains(newSegment))
        {
            // only originalSegment1 contains newSegment, add originalSegment1 statements to newSegment
            segmentToStatementMap.put(newSegment, statements1);
        }
        else if (originalSegment2.contains(newSegment))
        {
            // only originalSegment2 contains newSegment, add originalSegment2 statements to newSegment
            segmentToStatementMap.put(newSegment, statements2);
        }
    }

    private static List<Invoice> accountStatements(Segment segment, List<Statement> statements,
                                                   TeacherShareConfig teacherShareConfig, TeacherService teacherService,
                                                   ManagerShareConfig managerShareConfig)
    {
        long days = segment.days();

        double totalIncome = 0;
        Map<Long, BigDecimal> deductioners = new HashMap<Long, BigDecimal>();
        double totalDividendTeacherdRates = 0;
        double totalDividendManagerRates = 0;
        Map<Long, Invoice> dividendTeacherRates = new HashMap<Long, Invoice>();
        int teachNumber = 0;
        for (Statement statement: statements)
        {
            // sum up total income for each deductioner
            if (!deductioners.containsKey(statement.getDeductionId()))
            {
                totalIncome += ((BigDecimal)statement.getSettlementUnitPrice()).doubleValue() * days;
                deductioners.put(statement.getDeductionId(), statement.getSettlementUnitPrice());
            }

            // sum up total dividend rates
            long dividendId = statement.getDividendId();
            if (!dividendTeacherRates.containsKey(dividendId))
            {
                List<Segment> segments = new ArrayList<Segment>();
                segments.add(segment);
                Invoice invoice = new Invoice(dividendId, statement.getDividendName(),
                        statement.getRole(), (statement.getLevel() == null ? 0 : statement.getLevel()),
                        0, statement.getCampId(), statement.getCampTitle(),
                        segments, new BigDecimal(0), statement.getLastSettlementDate());

                if (statement.getRole() == ROLE_TEACHER)
                {
                    teachNumber++;
                    Teacher teacher = teacherService.findTeacherById(dividendId);
                    if (teacher != null) {
                        int dividendTeacherRate = teacherShareConfig.getTeacherShare(teacher.getStar());

                        totalDividendTeacherdRates += dividendTeacherRate;
                        invoice.setDividendRate(dividendTeacherRate);
                    }
                }

                if(statement.getRole() == ROLE_MANAGER)
                {
                    int dividendManagerRate = managerShareConfig.getShare();
                    totalDividendManagerRates += dividendManagerRate;
                    invoice.setDividendRate(dividendManagerRate);
                }

                dividendTeacherRates.put(dividendId, invoice);
            }
        }

        double averageDividendTeacherRates = totalDividendTeacherdRates  / teachNumber;
        double incomeDividable = totalIncome * totalDividendTeacherdRates  / teachNumber / 100;

        List<Invoice> accountedInvoices = new ArrayList<Invoice>();
        double teachersDividendPercent = (averageDividendTeacherRates - totalDividendManagerRates) / averageDividendTeacherRates;
        if (teachersDividendPercent <= 0)
        {
            logger.error("In segment: " + segment + ", totalIncome: " + totalIncome + ", incomeDividable: " + incomeDividable
                    + ", totalDividendTeacherdRates: " + totalDividendTeacherdRates + ", averageDividendTeacherRates: " + averageDividendTeacherRates
                    + ", totalDividendManagerRates: " + totalDividendManagerRates
                    + ", teachersDividendPercent: " + teachersDividendPercent + " is less than or equal to 0!!!");
            return accountedInvoices;
        }

        double managersDividendPercent = totalDividendManagerRates / averageDividendTeacherRates;
        logger.info("In segment: " + segment + ", totalIncome: " + totalIncome + ", incomeDividable: " + incomeDividable
                + ", totalDividendTeacherdRates: " + totalDividendTeacherdRates + ", averageDividendTeacherRates: " + averageDividendTeacherRates
                + ", totalDividendManagerRates: " + totalDividendManagerRates
                + ", teachersDividendPercent: " + teachersDividendPercent + ", managersDividendPercent: " + managersDividendPercent);

        for (Map.Entry<Long, Invoice> entry: dividendTeacherRates.entrySet())
        {
            Invoice invoice = entry.getValue();
            int dividendRate = invoice.getDividendRate();
            double dividendPercent = invoice.getRole() == ROLE_MANAGER ? managersDividendPercent : teachersDividendPercent;
            double totalDividendRates = invoice.getRole() == ROLE_MANAGER ? totalDividendManagerRates : totalDividendTeacherdRates;
            double teacherDividend = incomeDividable * dividendPercent * dividendRate / totalDividendRates;
            invoice.setSettlementAmount(formatResult(teacherDividend));
            logger.info("user id: " + invoice.getId() + " with dividendRate: " + dividendRate + " and income: " + invoice.getSettlementAmount());
            accountedInvoices.add(invoice);
        }

        return accountedInvoices;
    }

    private static BigDecimal formatResult(double doubleValue)
    {
        DecimalFormat df = new DecimalFormat("#.##");
        String formatString = df.format(doubleValue);
        BigDecimal result = new BigDecimal(formatString);
        return result;
    }
}