package com.platform.parent.mybatis.bean;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 * id INT(11) NOT NULL AUTO_INCREMENT,
 * user_id INT(11) NOT NULL,
 * camp_id INT(11) NOT NULL,
 * expiration_time TIMESTAMP NOT NULL, /* 训练营到期时间
 * role TINYINT(1) DEFAULT 0, /* 会员在训练营中的身份 0 普通成员, 1 管理员, 2 导师, 3 观察员, 4 编制外成员
 */
public class CampAttend {
    private long id;
    private long userId;
    private long campId;
    private long orderId;
    private Timestamp expiration, effectiveDate;
    private int role;

    /**public CampAttend(long userId, long campId, Timestamp expiration, int role) {
        this.userId = userId;
        this.campId = campId;
        this.expiration = expiration;
        this.role = role;
    }

    public CampAttend(long id, long userId, long campId, Timestamp expiration, int role) {
        this.id = id;
        this.userId = userId;
        this.campId = campId;
        this.expiration = expiration;
        this.role = role;
    }*/

    public CampAttend id (long id) {
        this.id = id;
        return this;
    }
    public CampAttend userId(long userId) {
        this.userId =userId;
        return this;
    }
    public CampAttend campId(long campId) {
        this.campId = campId;
        return this;
    }
    public CampAttend expiration(Timestamp expiration) {
        this.expiration = expiration;
        return this;
    }
    public CampAttend role (int role) {
        this.role = role;
        return this;
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

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public Timestamp getExpiration() {
        return expiration;
    }

    public void setExpiration(Timestamp expiration) {
        this.expiration = expiration;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Timestamp getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Timestamp effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
