package com.platform.parent.response.financial;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by dengb.
 */
public class Expense {
    private long userId;
    private String userName;
    private long campId;
    private String campTitle;
    private BigDecimal amount;
    private Timestamp transactionDate;

    public Expense(long userId, String userName, long campId, String campTitle,
                   BigDecimal amount, Timestamp transactionDate) {
        this.userId = userId;
        this.userName = userName;
        this.campId = campId;
        this.campTitle = campTitle;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal orderAmount) {
        this.amount = amount;
    }

    public Timestamp getTransactionDate()
    {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate)
    {
        this.transactionDate = transactionDate;
    }
}