package com.platform.parent.request.camp;

import java.math.BigDecimal;

/**
 * Created by tqyao.
 */
public class ApplyCampRequest {
    private long userId;//申请人id
    private BigDecimal price0, price1, price2;

    public ApplyCampRequest(long userId, BigDecimal price0, BigDecimal price1, BigDecimal price2) {
        this.userId = userId;
        this.price0 = price0;
        this.price1 = price1;
        this.price2 = price2;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice0() {
        return price0;
    }

    public void setPrice0(BigDecimal price0) {
        this.price0 = price0;
    }

    public BigDecimal getPrice1() {
        return price1;
    }

    public void setPrice1(BigDecimal price1) {
        this.price1 = price1;
    }

    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal price2) {
        this.price2 = price2;
    }
}
