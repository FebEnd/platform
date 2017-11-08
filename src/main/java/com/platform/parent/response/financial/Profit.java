package com.platform.parent.response.financial;

import java.math.BigDecimal;

/**
 * Created by dengb.
 */
public class Profit {
    private BigDecimal totalOrderAmount;
    private BigDecimal totalCouponAmount;
    private BigDecimal totalNetAmount;
    private BigDecimal totalExpense;
    private BigDecimal profit;


    public Profit(BigDecimal totalOrderAmount, BigDecimal totalCouponAmount, BigDecimal totalNetAmount,
                  BigDecimal totalExpense, BigDecimal profit) {
        this.totalOrderAmount = totalOrderAmount;
        this.totalCouponAmount = totalCouponAmount;
        this.totalNetAmount = totalNetAmount;
        this.totalExpense = totalExpense;
        this.profit = profit;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public BigDecimal getTotalCouponAmount() {
        return totalCouponAmount;
    }

    public void setTotalCouponAmount(BigDecimal totalCouponAmount) {
        this.totalCouponAmount = totalCouponAmount;
    }

    public BigDecimal getTotalNetAmount() {
        return totalNetAmount;
    }

    public void setTotalNetAmount(BigDecimal totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}