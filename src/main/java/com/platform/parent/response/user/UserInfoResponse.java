package com.platform.parent.response.user;

import java.sql.Date;

/**
 * Created by tqyao.
 */
public class UserInfoResponse {
    private long userId;
    private String nickname;
    private String phone;
    private Date childBirth;
    private String childGender;
    private String childGrade;
    private long childSchool;
    private String schoolAlias;
    private String avatar;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getChildBirth() {
        return childBirth;
    }

    public void setChildBirth(Date childBirth) {
        this.childBirth = childBirth;
    }

    public String getChildGender() {
        return childGender;
    }

    public void setChildGender(String childGender) {
        this.childGender = childGender;
    }

    public String getChildGrade() {
        return childGrade;
    }

    public void setChildGrade(String childGrade) {
        this.childGrade = childGrade;
    }

    public long getChildSchool() {
        return childSchool;
    }

    public void setChildSchool(long childSchool) {
        this.childSchool = childSchool;
    }

    public String getSchoolAlias() {
        return schoolAlias;
    }

    public void setSchoolAlias(String schoolAlias) {
        this.schoolAlias = schoolAlias;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
