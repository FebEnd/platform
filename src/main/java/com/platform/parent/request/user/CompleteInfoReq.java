package com.platform.parent.request.user;

/**
 * Created by tqyao.
 * id:2
 * phone:15001877058
 * nickname:testnickname
 * city:上海市
 * liveDistrict:松江区
 * targetDistrict:松江区
 * childBirth:20170831
 * childGender:男
 * childSchool:1
 */
public class CompleteInfoReq {
    private long id;
    private String phone, nickname, city, liveDistrict, targetDistrict,childBirth,childGender;
    private String childGrade;
    private long childSchool;

    public String getChildGrade() {
        return childGrade;
    }

    public void setChildGrade(String childGrade) {
        this.childGrade = childGrade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getChildBirth() {
        return childBirth;
    }

    public void setChildBirth(String childBirth) {
        this.childBirth = childBirth;
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
