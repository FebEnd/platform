package com.platform.parent.mybatis.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by tqyao.
 * id INT(11) NOT NULL AUTO_INCREMENT,
 * user_id INT(11) NOT NULL ,
 * name VARCHAR(50) NOT NULL ,
 * description TEXT ,
 * amount DECIMAL(10,2) NOT NULL ,
 * expiration TIMESTAMP NOT NULL , 过期日期
 * publish TIMESTAMP NOT NULL , 发行日期
 */
public class Coupon {
    private long id;
    private long userId, channel;
    private String name, description;
    private BigDecimal amount;
    private Timestamp expiration, publish;

    public Coupon id(long id) {
        this.id = id;
        return this;
    }

    public Coupon userId(long userId) {
        this.userId = userId;
        return this;
    }

    public Coupon channel(long channel) {
        this.channel = channel;
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
    public Coupon expiration(Timestamp expiration) {
        this.expiration = expiration;
        return this;
    }
    public Coupon publish(Timestamp publish) {
        this.publish = publish;
        return this;
    }

    /**public Coupon(long id, long userId, String name, String description, BigDecimal amount, Timestamp expiration, Timestamp publish) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.expiration = expiration;
        this.publish = publish;
    }

    public Coupon(long userId, String name, String description, BigDecimal amount, Timestamp expiration, Timestamp publish) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.expiration = expiration;
        this.publish = publish;
    }*/

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

    public Timestamp getExpiration() {
        return expiration;
    }

    public void setExpiration(Timestamp expiration) {
        this.expiration = expiration;
    }

    public Timestamp getPublish() {
        return publish;
    }

    public void setPublish(Timestamp publish) {
        this.publish = publish;
    }

    public long getChannel() {
        return channel;
    }

    public void setChannel(long channel) {
        this.channel = channel;
    }
}
