package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 */
public class SchoolAlias {
    private long id;
    private String alias;
    private long schoolId;

    public SchoolAlias id(long id) {
        this.id = id;
        return this;
    }
    public SchoolAlias alias(String alias) {
        this.alias = alias;
        return this;
    }
    public SchoolAlias schoolId(long schoolId) {
        this.schoolId = schoolId;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }
}
