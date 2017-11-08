package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.config.ManagerShareConfig;
import com.platform.parent.config.PriceConfig;
import com.platform.parent.config.TeacherShareConfig;
import com.platform.parent.mybatis.bean.Invoice;
import com.platform.parent.mybatis.bean.Statement;
import com.platform.parent.mybatis.bean.UserSettlement;
import com.platform.parent.mybatis.service.*;
import com.platform.parent.request.financial.SettlementReq;
import com.platform.parent.request.financial.SettlementReqList;
import com.platform.parent.response.financial.Expense;
import com.platform.parent.response.financial.Income;
import com.platform.parent.response.financial.Profit;
import com.platform.parent.util.EnumUtil;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.FinancialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dengb.
 */
@RestController
@RequestMapping(value = "/financial")
public class FinancialController {
    private static final Logger logger = LoggerFactory.getLogger(FinancialController.class);

    @Autowired
    StatementService statementService;

    @Autowired
    PriceConfig priceConfig;

    @Autowired
    TeacherShareConfig teacherShareConfig;

    @Autowired
    TeacherService teacherService;

    @Autowired
    ManagerShareConfig managerShareConfig;

    @Autowired
    UserSettlementService userSettlementService;

    @Autowired
    UserService userService;

    @Autowired
    CampService campService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public @ResponseBody Object getInvoices() {
        List<Statement> statements = this.statementService.findStatements();

        List<Invoice> invoices = FinancialUtil.processStatements(statements, teacherShareConfig, teacherService, managerShareConfig);
        JSONObject result = new JSONObject();
        result.put("status","0");
        result.put("message","成功");
        JSONObject data = new JSONObject();
        data.put("invoices", invoices);
        result.put("data", data);
        logger.info("Get invoices :\t" + invoices.size());
        return result;
    }

    @RequestMapping(value = "/userSettlement", method = RequestMethod.GET)
    public @ResponseBody Object getUserSettlementsBetweenTime(@RequestParam(required = true, value = "start") Date start,
                                                              @RequestParam(required = true, value = "end") Date end) {
        logger.info("getUserSettlementsBetweenTime, start: [" + start + "], end: [" + end + "]");

        Timestamp tsStart = new Timestamp(start.getTime());
        Timestamp tsEnd = new Timestamp(end.getTime());
        List<UserSettlement> userSettlements = this.userSettlementService.findUserSettlementBetweenTime(tsStart, tsEnd);

        JSONObject result = new JSONObject();
        result.put("status","0");
        result.put("message","成功");
        JSONObject data = new JSONObject();
        data.put("userSettlements", userSettlements);
        result.put("data", data);
        logger.info("Get serSettlementsu BetweenTime :" + userSettlements.size());
        return result;
    }

    @RequestMapping(value = "/income", method = RequestMethod.GET)
    public @ResponseBody Object getIncomeBetweenTime(@RequestParam(required = true, value = "start") Date start,
                                                              @RequestParam(required = true, value = "end") Date end) {
        logger.info("getIncomeBetweenTime, start: [" + start + "], end: [" + end + "]");

        Timestamp tsStart = new Timestamp(start.getTime());
        Timestamp tsEnd = new Timestamp(end.getTime());
        List<Income> incomes = this.userSettlementService.findIncomeBetweenTime(tsStart, tsEnd);

        JSONObject result = new JSONObject();
        result.put("status","0");
        result.put("message","成功");
        JSONObject data = new JSONObject();
        data.put("incomes", incomes);
        result.put("data", data);
        logger.info("Get incomes BetweenTime :" + incomes.size());
        return result;
    }

    @RequestMapping(value = "/expense", method = RequestMethod.GET)
    public @ResponseBody Object getExpenseBetweenTime(@RequestParam(required = true, value = "start") Date start,
                                                     @RequestParam(required = true, value = "end") Date end) {
        logger.info("getExpenseBetweenTime, start: [" + start + "], end: [" + end + "]");

        Timestamp tsStart = new Timestamp(start.getTime());
        Timestamp tsEnd = new Timestamp(end.getTime());
        List<Expense> expenses = this.userSettlementService.findExpenseBetweenTime(tsStart, tsEnd);

        JSONObject result = new JSONObject();
        result.put("status","0");
        result.put("message","成功");
        JSONObject data = new JSONObject();
        data.put("incomes", expenses);
        result.put("data", data);
        logger.info("Get expenses BetweenTime :" + expenses.size());
        return result;
    }

    @RequestMapping(value = "/profit", method = RequestMethod.GET)
    public @ResponseBody Object getProfitBetweenTime(@RequestParam(required = true, value = "start") Date start,
                                                      @RequestParam(required = true, value = "end") Date end) {
        logger.info("profitBetweenTime, start: [" + start + "], end: [" + end + "]");

        Timestamp tsStart = new Timestamp(start.getTime());
        Timestamp tsEnd = new Timestamp(end.getTime());
        Profit profit = this.userSettlementService.findProfitBetweenTime(tsStart, tsEnd);

        JSONObject result = new JSONObject();
        result.put("status","0");
        result.put("message","成功");
        JSONObject data = new JSONObject();
        data.put("profit", profit);
        result.put("data", data);
        return result;
    }

    @RequestMapping(value = "/transactionSettlement", method = RequestMethod.POST)
    @ResponseBody @Transactional
    public Object transactionSettlement(@Valid @RequestBody SettlementReqList settlementReqList, Errors errors) {
        if (errors.hasErrors())
        {
            List<ObjectError> objErrors= errors.getAllErrors();

            for (ObjectError objectError: objErrors)
            {
                logger.info("Validation errors: " + objectError.toString());
            }

            return EnumUtil.errorToJson(ErrorCode.FINANCIAL_BAD_SETTLEMENT_REQUEST, errors.toString());
        }

        Map<Long, List<Long>> campDividendUsers = new HashMap<Long, List<Long>>();
        Map<Long, Timestamp> campLastSettlementDate = new HashMap<Long, Timestamp>();
        List<SettlementReq> reqs = settlementReqList.getSettlementReqList();
        for(SettlementReq settlementReq: reqs)
        {
            long campId = settlementReq.getCampId();
            long dividendUserId = settlementReq.getUserId();
            if (campDividendUsers.containsKey(campId))
            {
                campDividendUsers.get(campId).add(dividendUserId);
            }
            else
            {
                List<Long> dividendUsers = new ArrayList<Long>();
                dividendUsers.add(dividendUserId);
                campDividendUsers.put(campId, dividendUsers);
            }

            if (!campLastSettlementDate.containsKey(campId))
            {
                campLastSettlementDate.put(campId, settlementReq.getCampLastSettlementDate());
            }
        }

        // validate users in a camp are transacted in a batch
        for(Map.Entry<Long, List<Long>> entry: campDividendUsers.entrySet())
        {
            long campId = entry.getKey();
            List<Long> dividendUsers = entry.getValue();
            List<Long> dividendCandidates = this.userSettlementService.findDividendUsersByCampId(campId);
            logger.info("Camp: " + campId + " passed in dividend users: " + dividendUsers + ", dividend candidates: " + dividendCandidates);

            final Set<Long> dividendUsersSet = new HashSet<Long>(dividendUsers);
            final Set<Long> dividendCandidatesSet = new HashSet<Long>(dividendCandidates);
            if (!dividendUsersSet.equals(dividendCandidatesSet))
            {
                return EnumUtil.errorToJson(ErrorCode.FINANCIAL_SETTLEMENT_REQUEST_NOT_CAMP_BASED);
            }
        }

        // step 1 call 3rd party transfer API?
        // TODO

         // step 2 persist transaction settlement
        for(SettlementReq settlementReq: reqs)
        {
            Object settlementResult = transactionSettlement(settlementReq);
            if (settlementResult != null)
            {
                return settlementResult;
            }
        }

        // step 3 update camp last settlement date
        for(Map.Entry<Long, Timestamp> entry: campLastSettlementDate.entrySet())
        {
            long id = entry.getKey();
            Timestamp lastSettlementDate = entry.getValue();
            logger.info("Update camp " + id + "'s last settlement date to " + lastSettlementDate);
            this.campService.updateLastSettlementDate(id, lastSettlementDate);
        }

        JSONObject result = new JSONObject();
        result.put("status","0");
        result.put("message","成功");
        return result;
    }

    private Object transactionSettlement(SettlementReq req)
    {
        // add user settlement
        UserSettlement userSettlement = new UserSettlement();
        userSettlement.userId(req.getUserId()).campId(req.getCampId()).role(req.getRole()).level(req.getLevel())
                .dividendRate(req.getDividendRate()).settlementAmount(req.getSettlementAmount());

        // only account users if they serve more than 1 month
        // TODO, or if their service end in the middle of this settlement period
        // TODO 2  save days to user settlement table....
        logger.info("Camp " + req.getCampId() + " user " + req.getUserId() + " days " + req.getDays());
        if(req.getDays() >= 30)
        {
            logger.info("About to close this settlement by setting settlement date to " + req.getSettlementDate());
            userSettlement.settlementDate(req.getSettlementDate());
        }

        int response = 0;
        UserSettlement existingUserSettlement = this.userSettlementService.findUserSettlementByCampUser(req.getCampId(), req.getUserId());
        if (existingUserSettlement != null)
        {
            if(existingUserSettlement.getSettlementDate() == null)
            {
                BigDecimal existingSettlementAmount = existingUserSettlement.getSettlementAmount();
                BigDecimal newSettlementAmount = existingSettlementAmount.add(userSettlement.getSettlementAmount());
                userSettlement.settlementAmount(newSettlementAmount);
                userSettlement.settlementDate(req.getSettlementDate());
                userSettlement.id(existingUserSettlement.getId());

                logger.info("Update existing settlement " + existingUserSettlement + " amount from "
                        + existingSettlementAmount + " to " + newSettlementAmount
                        + " and settlement date to " + req.getSettlementDate());
                response = this.userSettlementService.update(userSettlement);
                logger.info("Update response: " + response);
            }
            else
            {
                logger.info("User settlement already exists with valid settlement date, ignore this request.");
                response = 1;
            }
        }
        else
        {
            logger.info("Create new user settlement");
            response = this.userSettlementService.add(userSettlement);
            logger.info("Create response: " + response);
        }

        if (response <= 0) {
            logger.error("Create or update user settlement failed. Detail: userId:{}, campId:{}, role:{}, level:{}, dividendRate:{}" +
                            ", settlementDate:{}, settlementAmount:{}",
                    userSettlement.getUserId(), userSettlement.getCampId(), userSettlement.getRole(),
                    userSettlement.getLevel(), userSettlement.getDividendRate(),
                    userSettlement.getSettlementDate(), userSettlement.getSettlementAmount());
            return EnumUtil.errorToJson(ErrorCode.FINANCIAL_CREATE_OR_UPDATE_USER_SETTLEMENT_FAILED);
        }

        return null;
    }
}
