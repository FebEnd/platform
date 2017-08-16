package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 */
public class Member {
    private long id;
    private int vip;
    private long referenceId;

    public Member id(long id) {
        this.id = id;
        return this;
    }
    public Member vip(int vip) {
        this.vip = vip;
        return this;
    }

    public Member referenceId(long referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecommenderId() {
        return referenceId;
    }

    public void setRecommenderId(long recommenderId) {
        this.referenceId = recommenderId;
    }

    public long getId() {
        return id;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    /*public Member(long id, int vip) {

        this.id = id;
        this.vip = vip;
    }*/
}
