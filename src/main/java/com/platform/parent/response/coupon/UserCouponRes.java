package com.platform.parent.response.coupon;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by tqyao.
 */
public class UserCouponRes {
    private String name, description;
    private BigDecimal amount;
    private Timestamp publish, expiration;
    private boolean used;

    public UserCouponRes(String name, String description, BigDecimal amount, Timestamp publish, Timestamp expiration, boolean used) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.publish = publish;
        this.expiration = expiration;
        this.used = used;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getPublish() {
        return publish;
    }

    public void setPublish(Timestamp publish) {
        this.publish = publish;
    }

    public Timestamp getExpiration() {
        return expiration;
    }

    public void setExpiration(Timestamp expiration) {
        this.expiration = expiration;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
