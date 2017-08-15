package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 */
public class Role {
    private long id;
    private String name;

    public Role name(String name) {
        this.name = name;
        return this;
    }

    public Role id(long id) {
        this.id = id;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
