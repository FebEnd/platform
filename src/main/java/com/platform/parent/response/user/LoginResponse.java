package com.platform.parent.response.user;

/**
 * Created by tqyao.
 */
public class LoginResponse {
    private int status;
    private String token;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponse(int status, String token) {

        this.status = status;
        this.token = token;
    }
}
