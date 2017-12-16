package com.platform.parent.request.auth;

/**
 * Created by tqyao.
 */
public class RegisterReq {
    private String phone,number,channel;
    private int register;

    public RegisterReq() {
    }

    public RegisterReq(String phone, String number, String channel, int register) {
        this.phone = phone;
        this.number = number;
        this.channel = channel;
        this.register = register;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }
}
