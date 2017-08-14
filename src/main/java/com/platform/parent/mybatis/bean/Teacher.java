package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 */
public class Teacher {
    private long id;
    private String account;

    public Teacher(long id, String account) {
        this.id = id;
        this.account = account;
    }

    public Teacher() {
    }

    public Teacher account(String account) {
        this.account = account;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
