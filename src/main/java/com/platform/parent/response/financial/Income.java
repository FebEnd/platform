package com.platform.parent.response.financial;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by dengb.
 */
public class Income {
    private long userId;
    private String userName;
    private long campId;
    private String campTitle;
    private BigDecimal orderAmount, couponAmount, netAmount;
    private Timestamp transactionDate;

    public Income(long userId, String userName, long campId, String campTitle, BigDecimal orderAmount,
                  BigDecimal couponAmount, BigDecimal netAmount, Timestamp transactionDate) {
        this.userId = userId;
        this.userName = userName;
        this.campId = campId;
        this.campTitle = campTitle;
        this.orderAmount = orderAmount;
        this.couponAmount = couponAmount;
        this.netAmount = netAmount;
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
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