package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 * id BIGINT(20) NOT NULL AUTO_INCREMENT,
 * name TEXT NOT NULL ,
 */
public class Tag {
    private long id;
    private String name;

    public Tag id(long id) {
        this.id = id;
        return this;
    }

    public Tag name(String name) {
        this.name = name;
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

    /*public Tag(String name) {

        this.name = name;
    }

    public Tag(long id, String name) {

        this.id = id;
        this.name = name;
    }*/
}
