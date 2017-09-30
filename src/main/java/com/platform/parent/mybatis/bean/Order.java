package com.platform.parent.mybatis.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by tqyao.
 * id INT(11) NOT NULL AUTO_INCREMENT,
 * user_id INT(11) NOT NULL,
 * amount DECIMAL(10,2) NOT NULL,
 * type INT(11) NOT NULL, /* -1 表示购买vip, 其余与训练营id配对表示为加入训练营付费
 * create TIMESTAMP NOT NULL, #订单创建时间
 * payed TIMESTAMP, #订单支付时间
 * confirm TINYINT(1) NOT NULL DEFAULT TRUE ,#订单确认，默认确认，如果为false表示已取消
 * coupons TEXT,#订单中使用的优惠券id（将被锁定）
 */
public class Order {
    private long id;
    private long userId;
    private BigDecimal amount;
    private long type;
    private Timestamp create, payed;
    private boolean confirm;
    private String coupons;
    private int duration;

    public Order id(long id) {
        this.id = id;
        return this;
    }

    public Order duration(int duration) {
        this.duration = duration;
        return this;
    }

    public Order confirm(boolean confirm) {
        this.confirm = confirm;
        return this;
    }

    public Order coupons(String coupons) {
        this.coupons = coupons;
        return this;
    }

    public Order payed(Timestamp payed) {
        this.payed = payed;
        return this;
    }

    public Order userId(long userId) {
        this.userId = userId;
        return this;
    }

    public Order amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Order type(long type) {
        this.type = type;
        return this;
    }

    public Order create(Timestamp create) {
        this.create = create;
        return this;
    }

    /**
     * public Order(long id, long userId, BigDecimal amount, int type, Timestamp time) {
     * this.id = id;
     * this.userId = userId;
     * this.amount = amount;
     * this.type = type;
     * this.time = time;
     * }
     * <p>
     * public Order(long userId, BigDecimal amount, int type, Timestamp time) {
     * this.userId = userId;
     * this.amount = amount;
     * this.type = type;
     * this.time = time;
     * }
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }


    public Timestamp getPayed() {
        return payed;
    }

    public void setPayed(Timestamp payed) {
        this.payed = payed;
    }

    public String getCoupons() {
        return coupons;
    }

    public void setCoupons(String coupons) {
        this.coupons = coupons;
    }

    public Timestamp getCreate() {
        return create;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
