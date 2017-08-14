package com.platform.parent.response.user;

/**
 * Created by tqyao.
 */
public class LoginResponse {

    private String status, token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponse(String status, String token) {

        this.status = status;
        this.token = token;
    }
}
