package com.platform.parent.mybatis.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dengb.
 * 待转账清单
 */
public class Invoice {
    /* 待转账用户ID */
    private long id;
    /* 待转账用户名字 */
    private String name;
    /* 会员在训练营中的身份 0 普通成员, 1 管理员, 2 导师, 3 观察员, 4 编制外成员 */
    private int role;
    /* 待转账用户等级 */
    private int level;
    /* 待转账用户分成比例. with % omitted: 80 f0r 80%, 100 for 100% etc. */
    private int dividendRate;
    /* 待转账用户参与的训练营id */
    private long campId;
    /* 待转账用户参与的训练营名 */
    private String campDescription;
    /* 待转账用户在参与的训练营中的服务时间段 */
    private List<Segment> segments;
    /* 待转账用户的分成收入 */
    private BigDecimal settlementAmount;
    // 本次结算周期终止日， 用于更新相关训练营的结算日
    private Timestamp lastSettlementDate;
    // 会员在本次结算周期内的服务天数
    private int days;

    public Invoice(long id, String name, int role, int level, int dividendRate,
                   long campId, String campDescription, List<Segment> segments, BigDecimal income, Timestamp lastSettlementDate){
        this.id = id;
        this.name = name;
        this.role = role;
        this.level = level;
        this.dividendRate = dividendRate;
        this.campId = campId;
        this.campDescription = campDescription;
        this.segments = segments;
        this.settlementAmount = income;
        this.lastSettlementDate = lastSettlementDate;
    }

    public String toString()
    {
        return "id: " + id + ", name: " + name + ", role： " + role + ", level： " + level
                + ", dividendRate： " + dividendRate + ", campId： " + campId + ", campDescription： "
                + campDescription + ", segments： " + segments + ", settlementAmount: " + settlementAmount +
                "lastSettlementDate: " + lastSettlementDate + "\n";
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
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

    public int getDividendRate()
    {
        return dividendRate;
    }

    public void setDividendRate(int dividendRate)
    {
        this.dividendRate = dividendRate;
    }

    public long getCampId()
    {
        return campId;
    }

    public String getCampDescription()
    {
        return campDescription;
    }

    public List<Segment> getSegments()
    {
        return segments;
    }

    public void setSegments(List<Segment> segments)
    {
        this.segments = segments;
    }

    public BigDecimal getSettlementAmount()
    {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount)
    {
        this.settlementAmount = settlementAmount;
    }

    public Timestamp getLastSettlementDate()
    {
        return lastSettlementDate;
    }

    public void setLastSettlementDate(Timestamp lastSettlementDate)
    {
        this.lastSettlementDate = lastSettlementDate;
    }

    public int getDays()
    {
        int days = 0;
        if (segments != null)
        {
            for(Segment segment: segments)
            {
                days += segment.days();
            }
        }

        return days;
    }
}