package com.platform.parent.request.financial;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by dengb.
 */
public class SettlementReq {
    @NotNull
    private long userId, campId;
    @NotNull
    private int role;
    @NotNull
    private int level;
    // 分成比例，百分号前的数字 （如：80表示80%）
    @NotNull
    private int dividendRate;
    @Min(1)
    private int days;
    // 转账给用户的交易日期
    @NotNull
    private Timestamp settlementDate;
    // 结算金额
    @NotNull
    private BigDecimal settlementAmount;
    // 结算周期终止日期
    @NotNull
    private Timestamp campLastSettlementDate;

    public SettlementReq(){

    }

    public SettlementReq(long userId, long campId, int role, int level,
                         int dividendRate, Timestamp settlementDate, BigDecimal settlementAmount, Timestamp campLastSettlementDate)
    {
        this.userId = userId;
        this.campId = campId;
        this.role = role;
        this.level = level;
        this.dividendRate = dividendRate;
        this.settlementDate = settlementDate;
        this.settlementAmount = settlementAmount;
        this.campLastSettlementDate = campLastSettlementDate;
    }

    public String toString()
    {
        return "userId: " + userId + ", campId: " + campId + ", role: " + role
                + "level: " + level + ", dividendRate: " + dividendRate  + ", days: " + days
                + ", settlementDate: " + settlementDate + ", settlementAmount: "
                + settlementAmount + ", campLastSettlementDate: " + campLastSettlementDate + "\n";
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDividendRate() {
        return dividendRate;
    }

    public void setDividendRate(int dividendRate) {
        this.dividendRate = dividendRate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Timestamp getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Timestamp settlementDate) {
        this.settlementDate = settlementDate;
    }

    public BigDecimal getSettlementAmount()
    {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount)
    {
        this.settlementAmount = settlementAmount;
    }

    public Timestamp getCampLastSettlementDate() {
        return campLastSettlementDate;
    }

    public void setCampLastSettlementDate(Timestamp campLastSettlementDate) {
        this.campLastSettlementDate = campLastSettlementDate;
    }
}
