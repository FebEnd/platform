package com.platform.parent.mybatis.bean;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 */
public class Member {
    private long id;
    private Timestamp vip;

    public Member id(long id) {
        this.id = id;
        return this;
    }
    public Member vip(Timestamp vip) {
        this.vip = vip;
        return this;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Timestamp getVip() {
        return vip;
    }

    public void setVip(Timestamp vip) {
        this.vip = vip;
    }

    /*public Member(long id, int vip) {

        this.id = id;
        this.vip = vip;
    }*/
}
