package com.platform.parent.mybatis.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by tqyao.
 * id INT(11) NOT NULL AUTO_INCREMENT,
 * user_id INT(11) NOT NULL,
 * amount DECIMAL(10,2) NOT NULL,
 * type INT(11) NOT NULL, /* -1 表示购买vip, 其余与训练营id配对表示为加入训练营付费
 * time TIMESTAMP NOT NULL,
 */
public class Balance {
    private long id;
    private long userId;
    private BigDecimal amount;
    private int type;
    private Timestamp time;

    public Balance(long id, long userId, BigDecimal amount, int type, Timestamp time) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.time = time;
    }

    public Balance(long userId, BigDecimal amount, int type, Timestamp time) {
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.time = time;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
