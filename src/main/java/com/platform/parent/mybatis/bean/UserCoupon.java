package com.platform.parent.mybatis.bean;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 * 记录用户获得优惠券的记录的表
 * # user_id 这笔优惠券记录属于哪个user
 * # coupon_id 这笔优惠券记录发放的是哪个优惠券
 * # channel 来自于什么渠道（或者由于邀请了哪个用户）
 * # publish 优惠券发放日期
 * # expiration 优惠券过期日期
 */
public class UserCoupon {
    private long id, userId;
    private int couponId;
    private Timestamp publish, expiration;

    public UserCoupon id(long id) {
        this.id = id;
        return this;
    }
    public UserCoupon userId(long userId) {
        this.userId = userId;
        return this;
    }
    public UserCoupon couponId(int couponId) {
        this.couponId = couponId;
        return this;
    }
    /*public UserCoupon channel(long channel) {
        this.channel = channel;
        return this;
    }*/
    public UserCoupon publish(Timestamp publish) {
        this.publish = publish;
        return this;
    }
    public UserCoupon expiration(Timestamp expiration) {
        this.expiration = expiration;
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

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    /*public long getChannel() {
        return channel;
    }

    public void setChannel(long channel) {
        this.channel = channel;
    }*/

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
}
