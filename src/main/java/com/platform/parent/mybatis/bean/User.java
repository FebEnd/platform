package com.platform.parent.mybatis.bean;

import java.util.List;

/**
 * Created by tqyao.
 */
public class User {
    private long id;
    private String phone, nickname, password, avatar;
    private long referee;
    private int auth;//申请实名认证 0 未申请， 1 已申请未审核， 2 已审核通过， 3 未通过
    private int register;//注册途径 0 小程序，1 app

    private UserDetail detail;

    private List<Role> roles;

    public User id(long id) {
        this.id = id;
        return this;
    }

    public User avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public User phone(String phone) {
        this.phone = phone;
        return this;
    }

    public User auth(int auth) {
        this.auth = auth;
        return this;
    }

    public User referee(long referee) {
        this.referee = referee;
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

    public User detail(UserDetail detail) {
        this.detail = detail;
        return this;
    }

    public User register(int register) {
        this.register = register;
        return this;
    }

    /*public User city(String city) {
        this.city = city;
        return this;
    }

    public User liveDistrict(String liveDistrict) {
        this.liveDistrict = liveDistrict;
        return this;
    }

    public User targetDistrict(String targetDistrict) {
        this.targetDistrict = targetDistrict;
        return this;
    }

    public User childBirth(Date childBirth) {
        this.childBirth = childBirth;
        return this;
    }

    public User childGrade(String childGrade) {
        this.childGrade = childGrade;
        return this;
    }

    public User childGender(String childGender) {
        this.childGender = childGender;
        return this;
    }

    public User childSchool(String childSchool) {
        this.childSchool = childSchool;
        return this;
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

    /*public String getCity() {
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
    }*/

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

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public UserDetail getDetail() {
        return detail;
    }

    public void setDetail(UserDetail detail) {
        this.detail = detail;
    }

    public long getReferee() {
        return referee;
    }

    public void setReferee(long referee) {
        this.referee = referee;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }
}
