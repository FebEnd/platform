package com.platform.parent.response.auth;

/**
 * Created by tqyao.
 */
public class VerifyRes {
    private int status;
    private boolean exists;

    public VerifyRes(int status, boolean exists) {
        this.status = status;
        this.exists = exists;
    }

    public VerifyRes(boolean exists) {
        this.exists = exists;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isExists() {

        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
