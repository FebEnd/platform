package com.platform.parent.request.user;

/**
 * Created by tqyao.
 */
public class ApplyAuthReq {
    private long id;
    private int childGrade;
    private long childSchool;
    private String childGender;

    public ApplyAuthReq id(long id) {
        this.id = id;
        return this;
    }
    public ApplyAuthReq childGrade(int childGrade) {
        this.childGrade = childGrade;
        return this;
    }
    public ApplyAuthReq childGender(String childGender) {
        this.childGender = childGender;
        return this;
    }
    public ApplyAuthReq childSchool(long childSchool) {
        this.childSchool = childSchool;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
