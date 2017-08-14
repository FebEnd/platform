package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 */
public class Member {
    private long id;
    private int vip;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public Member(long id, int vip) {

        this.id = id;
        this.vip = vip;
    }
}
