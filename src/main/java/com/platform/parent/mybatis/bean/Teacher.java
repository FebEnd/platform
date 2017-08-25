package com.platform.parent.mybatis.bean;

import java.util.List;

/**
 * Created by tqyao.
 */
public class Teacher {
    private long id;
    private String account;//银行账户
    private int star;
    private String description;

    private List<Tag> tags;

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

    public Teacher tags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Teacher description(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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
