package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 */
public class Teacher {
    private long id;
    private String account;//银行账户
    private int star;

    public Teacher id(long id) {
        this.id = id;
        return this;
    }

    public Teacher star(int star) {
        this.star = star;
        return this;
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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
