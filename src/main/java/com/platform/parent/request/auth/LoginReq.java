package com.platform.parent.request.auth;

/**
 * Created by tqyao.
 */
public class LoginReq {
    private String phone, number;

    public LoginReq(String phone, String number) {
        this.phone = phone;
        this.number = number;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
