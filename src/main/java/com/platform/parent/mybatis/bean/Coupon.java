package com.platform.parent.mybatis.bean;

import java.math.BigDecimal;

/**
 * Created by tqyao.
 * #优惠券模板表
 * # name 优惠券名称
 * # description 优惠券描述
 * # amount 优惠券的优惠金额
 * # duration 优惠券有效期（天数）
 */
public class Coupon {
    private int id, duration;
    private String name, description;
    private BigDecimal amount;

    public Coupon id(int id) {
        this.id = id;
        return this;
    }
    public Coupon duration(int duration) {
        this.duration = duration;
        return this;
    }
    public Coupon name(String name) {
        this.name = name;
        return this;
    }
    public Coupon description(String description) {
        this.description = description;
        return this;
    }
    public Coupon amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
}
