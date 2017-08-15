package com.platform.parent.mybatis.bean;

import java.sql.Date;
import java.util.List;

/**
 * Created by tqyao.
 */
public class User {
    private long id;
    private String phone, nickname, password, city, liveDistrict, targetDistrict;
    private Date childBirth;
    private String childGrade;
    private String childGender, childSchool;

    private List<Role> roles;

    public User phone(String phone) {
        this.phone = phone;
        return this;
    }

    public User nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public User city(String city) {
        this.city = city;
        return this;
    }

    public User liveDistrict(String liveDistrict) {this.liveDistrict = liveDistrict; return this;}

    public User targetDistrict(String targetDistrict) {this.targetDistrict=targetDistrict; return this;}

    public User childBirth(Date childBirth) {this.childBirth = childBirth; return this;}

    public User childGrade(String childGrade) {this.childGrade = childGrade; return this;}

    public User childGender(String childGender) {this.childGender = childGender; return this;}

    public User childSchool(String childSchool) {this.childSchool = childSchool; return this;}

    /*public User(long id, String phone, String nickname, String password, String city, String liveDistrict, String targetDistrict, Date childBirth, String childGrade, String childGender, String childSchool) {
        this.id = id;
        this.phone = phone;
        this.nickname = nickname;
        this.password = password;
        this.city = city;
        this.liveDistrict = liveDistrict;
        this.targetDistrict = targetDistrict;
        this.childBirth = childBirth;
        this.childGrade = childGrade;
        this.childGender = childGender;
        this.childSchool = childSchool;
    }


    public User(String phone, String nickname, String password) {
        this.phone = phone;
        this.nickname = nickname;
        this.password = password;
    }

    public User(long id, String phone, String nickname, String password) {
        this.id = id;
        this.phone = phone;
        this.nickname = nickname;
        this.password = password;
    }*/

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

    public Date getChildBirth() {
        return childBirth;
    }

    public void setChildBirth(Date childBirth) {
        this.childBirth = childBirth;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
