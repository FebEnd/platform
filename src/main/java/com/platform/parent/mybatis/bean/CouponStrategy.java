package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 * id INT(11) NOT NULL AUTO_INCREMENT,
 * channel INT(11) NOT NULL ,
 * base_id INT(11) NOT NULL ,
 * number INT(11) NOT NULL ,
 */
public class CouponStrategy {
    private int id;
    private int channel, baseId, number;

    public CouponStrategy id(int id){
        this.id = id;
        return this;
    }
    public CouponStrategy channel(int channel) {
        this.channel = channel;
        return this;
    }
    public CouponStrategy baseId(int baseId) {
        this.baseId = baseId;
        return this;
    }
    public CouponStrategy number(int number) {
        this.number = number;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getBaseId() {
        return baseId;
    }

    public void setBaseId(int baseId) {
        this.baseId = baseId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
