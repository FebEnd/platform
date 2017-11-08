package com.platform.parent.mybatis.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by dengb.
 */
public class UserSettlement {
    private long id;
    private long userId;
    private long campId;
    private int role;
    private int level;
    private int dividendRate; // with % omitted: 80 f0r 80%, 100 for 100% etc.
    private Timestamp settlementDate;
    private BigDecimal settlementAmount;

    public String toString()
    {
        return "id: " + id + ", userId: " + userId + ", campId: " + campId + ", role: " + role
                + ", level: " + level + ", dividendRate: " + dividendRate + ", settlementDate: "
                + settlementDate + ", settlementAmount: " + settlementAmount + "\n";
    }

    public UserSettlement id(long id)
    {
        this.id = id;
        return this;
    }

    public UserSettlement userId(long userId)
    {
        this.userId = userId;
        return this;
    }

    public UserSettlement campId(long campId)
    {
        this.campId = campId;
        return this;
    }

    public UserSettlement role(int role)
    {
        this.role = role;
        return this;
    }

    public UserSettlement level(int level)
    {
        this.level = level;
        return this;
    }

    public UserSettlement dividendRate(int dividendRate)
    {
        this.dividendRate = dividendRate;
        return this;
    }

    public UserSettlement settlementDate(Timestamp settlementDate)
    {
        this.settlementDate = settlementDate;
        return this;
    }

    public UserSettlement settlementAmount(BigDecimal settlementAmount)
    {
        this.settlementAmount = settlementAmount;
        return this;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public long getCampId()
    {
        return campId;
    }

    public void setCampId(long campId)
    {
        this.campId = campId;
    }

    public int getRole()
    {
        return role;
    }

    public void setRole(int role)
    {
        this.role = role;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getDividendRate()
    {
        return dividendRate;
    }

    public void setDividendRate(int dividendRate)
    {
        this.dividendRate = dividendRate;
    }

    public Timestamp getSettlementDate()
    {
        return settlementDate;
    }

    public void setSettlementDate(Timestamp settlementDate)
    {
        this.settlementDate = settlementDate;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount)
    {
        this.settlementAmount = settlementAmount;
    }
}