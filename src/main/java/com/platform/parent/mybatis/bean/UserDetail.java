package com.platform.parent.mybatis.bean;

import com.platform.parent.util.DateUtil;

import java.sql.Date;

/**
 * Created by tqyao.
 * id BIGINT(20) NOT NULL ,
 * city VARCHAR(50),
 * live_district VARCHAR(50),
 * target_district VARCHAR(50),
 * child_birth DATE,
 * child_grade VARCHAR(20),
 * child_gender VARCHAR(10),
 * child_school VARCHAR(100),
 */
public class UserDetail {
    private long id;
    private String city;
    private String liveDistrict;
    private String targetDistrict;
    private Date childBirth;
    private int childGrade;
    private String childGender;
    private long childSchool;

    public UserDetail id(long id) {
        this.id = id;
        return this;
    }
    public UserDetail city(String city) {
        this.city = city;
        return this;
    }
    public UserDetail liveDistrict(String liveDistrict) {
        this.liveDistrict = liveDistrict;
        return this;
    }
    public UserDetail targetDistrict(String targetDistrict) {
        this.targetDistrict = targetDistrict;
        return this;
    }
    public UserDetail childBirth(String childBirth) {
        this.childBirth = DateUtil.stringToSqlDate(childBirth);
        return this;
    }
    public UserDetail childGrade(int childGrade) {
        this.childGrade = childGrade;
        return this;
    }
    public UserDetail childGender(String childGender) {
        this.childGender = childGender;
        return this;
    }
    public UserDetail childSchool(long childSchool) {
        this.childSchool = childSchool;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLiveDistrict() {
        return liveDistrict;
    }

    public void setLiveDistrict(String liveDistrict) {
        this.liveDistrict = liveDistrict;
    }

    public String getTargetDistrict() {
        return targetDistrict;
    }

    public void setTargetDistrict(String targetDistrict) {
        this.targetDistrict = targetDistrict;
    }

    public Date getChildBirth() {
        return childBirth;
    }

    public void setChildBirth(Date childBirth) {
        this.childBirth = childBirth;
    }

    public int getChildGrade() {
        return childGrade;
    }

    public void setChildGrade(int childGrade) {
        this.childGrade = childGrade;
    }

    public String getChildGender() {
        return childGender;
    }

    public void setChildGender(String childGender) {
        this.childGender = childGender;
    }

    public long getChildSchool() {
        return childSchool;
    }

    public void setChildSchool(long childSchool) {
        this.childSchool = childSchool;
    }
}
