package com.platform.parent.mybatis.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dengb.
 */
public class Statement {
    /* 分成用户的ID */
    private long dividendId;
    /* 分成用户的名字 */
    private String dividendName;
    /* 会员在训练营中的身份 0 普通成员, 1 管理员, 2 导师, 3 观察员, 4 编制外成员 */
    private int role;
    private Integer level;
    private long campId;
    private String campTitle;
    /* 产生收入的用户ID */
    private long deductionId;
    /* 产生收入的用户名 */
    private String deductionName;
    private Timestamp billStartDate;
    private Timestamp billEndDate;
    private long settlementDays;
    private BigDecimal settlementUnitPrice;
    private BigDecimal income;
    private List<Segment> segments;
    private Timestamp lastSettlementDate;

    public Statement(long dividendId, long deductionId, Timestamp billStartDate, Timestamp billEndDate,
                     long settlementDays, BigDecimal settlementUnitPrice) {
        this.dividendId = dividendId;
        this.deductionId = deductionId;
        this.billStartDate = billStartDate;
        this.billEndDate = billEndDate;
        this.settlementDays = settlementDays;
        this.settlementUnitPrice = settlementUnitPrice;
    }

    public Statement(long dividendId, String dividendName, int role, Integer level, long campId, String campTitle,
                     long deductionId, String deductionName,Timestamp billStartDate, Timestamp billEndDate,
                     long settlementDays, BigDecimal settlementUnitPrice, Timestamp lastSettlementDate) {
        this.dividendId = dividendId;
        this.dividendName = dividendName;
        this.role = role;
        this.level = level;
        this.campId = campId;
        this.campTitle = campTitle;
        this.deductionId = deductionId;
        this.deductionName = deductionName;
        this.billStartDate = billStartDate;
        this.billEndDate = billEndDate;
        this.settlementDays = settlementDays;
        this.settlementUnitPrice = settlementUnitPrice;
        this.lastSettlementDate = lastSettlementDate;
    }
    
    public Statement(long dividendId, BigDecimal income) {
        this.dividendId = dividendId;
        this.income = income;
    }

    public String toString()
    {
        return "dividendId: " + dividendId + ", dividendName: " + dividendName + ", role： " + role + ", level： " + level
                + ", campId： " + campId + ", campTitle： " + campTitle + ", deductionId： " + deductionId
                + ", deductionName： " + deductionName + ", billStartDate: " + billStartDate
                + ", billEndDate: " + billEndDate + ", settlementDays: "
                + settlementDays + ", settlementUnitPrice: " + settlementUnitPrice
                + ", income: " + income + ", segments: " + segments + ", lastSettlementDate" + lastSettlementDate + "\n";
    }

    public Segment getSegment()
    {
        return new Segment(this.billStartDate, this.billEndDate);
    }

    public Statement dividendId (long dividendId) {
        this.dividendId = dividendId;
        return this;
    }

    public Statement dividendName (String dividendName) {
        this.dividendName = dividendName;
        return this;
    }

    public Statement role (int role) {
        this.role = role;
        return this;
    }

    public Statement level (Integer level) {
        this.level = level;
        return this;
    }

    public Statement campId (long campId) {
        this.campId = campId;
        return this;
    }

    public Statement deductionId(long deductionId) {
        this.deductionId = deductionId;
        return this;
    }

    public Statement deductionName(String deductionName) {
        this.deductionName = deductionName;
        return this;
    }

    public Statement billStartDate(Timestamp billStartDate) {
        this.billStartDate = billStartDate;
        return this;
    }

    public Statement billEndDate(Timestamp billEndDate) {
        this.billEndDate = billEndDate;
        return this;
    }

    public Statement settlementDays (long settlementDays) {
        this.settlementDays = settlementDays;
        return this;
    }

    public Statement settlementUnitPrice (BigDecimal settlementUnitPrice) {
        this.settlementUnitPrice = settlementUnitPrice;
        return this;
    }

    public Statement lastSettlementDate (Timestamp lastSettlementDate) {
        this.lastSettlementDate = lastSettlementDate;
        return this;
    }

    public long getDividendId() {
        return dividendId;
    }

    public void setDividendId(long dividendId) {
        this.dividendId = dividendId;
    }

    public String getDividendName() {
        return dividendName;
    }

    public void setDividendName(String dividendName) {
        this.dividendName = dividendName;
    }

    public int getRole()
    {
        return role;
    }

    public void setRole(int role)
    {
        this.role = role;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public String getCampTitle() {
        return campTitle;
    }

    public void setCampTitle(String campTitle) {
        this.campTitle = campTitle;
    }

    public long getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(long deductionId) {
        this.deductionId = deductionId;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public void setDeductionName(String eductionName) {
        this.deductionName = deductionName;
    }

    public Timestamp getBillStartDate() {
        return billStartDate;
    }

    public void setBillStartDate(Timestamp billStartDate) {
        this.billStartDate = billStartDate;
    }

    public Timestamp getBillEndDate() {
        return billEndDate;
    }

    public void setBillEndDate(Timestamp billEndDate) {
        this.billEndDate = billEndDate;
    }

    public long getSettlementDays() {
        return settlementDays;
    }

    public void setSettlementDays(long settlementDays) {
        this.settlementDays = settlementDays;
    }

    public BigDecimal getSettlementUnitPrice() {
        return settlementUnitPrice;
    }

    public void setSettlementUnitPrice(BigDecimal settlementUnitPrice) {
        this.settlementUnitPrice = settlementUnitPrice;
    }

    public BigDecimal getIncome()
    {
        return income;
    }

    public void setIncome(BigDecimal income)
    {
        this.income = income;
    }

    public List<Segment> getSegments()
    {
        return segments;
    }

    public void setSegments(List<Segment> segments)
    {
        this.segments = segments;
    }

    public Timestamp getLastSettlementDate()
    {
        return lastSettlementDate;
    }

    public void setLastSettlementDate(Timestamp lastSettlementDate)
    {
        this.lastSettlementDate = lastSettlementDate;
    }
}
