package com.platform.parent.util;

/**
 * Created by tqyao.
 */
public enum SuccessCode {
    ADD_SUCCESSFULLY(0,"ADD_SUCCESSFULLY","添加成功"),
    UPDATE_SUCCESSFULLY(0,"UPDATE_SUCCESSFULLY","修改成功"),
    DELETE_SUCCESSFULLY(0, "DELETE_SUCCESSFULLY","删除成功"),
    APPLY_CAMP_SUCCESSFULLY(0,"APPLY_CAMP_SUCCESSFULLY","申请开班成功"),
    APPLY_AUTH_SUCCESSFULLY(0,"APPLY_AUTH_SUCCESSFULLY","申请实名认证成功"),
    APPLY_TEACHER_SUCCESSFULLY(0,"APPLY_TEACHER_SUCCESSFULLY","申请成为导师成功");
    private int status;
    private String code, message;

    private SuccessCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
