package com.platform.parent.request.user;

/**
 * Created by tqyao.
 */
public class ApplyAuthReq {
    private long id;
    private String childGrade, childGender, childSchool;

    public ApplyAuthReq id(long id) {
        this.id = id;
        return this;
    }
    public ApplyAuthReq childGrade(String childGrade) {
        this.childGrade = childGrade;
        return this;
    }
    public ApplyAuthReq childGender(String childGender) {
        this.childGender = childGender;
        return this;
    }
    public ApplyAuthReq childSchool(String childSchool) {
        this.childSchool = childSchool;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChildGrade() {
        return childGrade;
    }

    public void setChildGrade(String childGrade) {
        this.childGrade = childGrade;
    }

    public String getChildGender() {
        return childGender;
    }

    public void setChildGender(String childGender) {
        this.childGender = childGender;
    }

    public String getChildSchool() {
        return childSchool;
    }

    public void setChildSchool(String childSchool) {
        this.childSchool = childSchool;
    }
}
