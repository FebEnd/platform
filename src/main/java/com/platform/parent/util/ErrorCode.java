package com.platform.parent.util;

/**
 * Created by tqyao.
 */
public enum ErrorCode {
    KEYWORD_IS_NULL(400,"KEYWORD_IS_NULL","关键词为空"),
    NO_SUCH_SCHOOL(400,"NO_SUCH_SCHOOL","没有相关学校"),
    QUERY_SCHOOL_FAILED(400,"QUERY_SCHOOL_FAILED","查询学校失败"),
    ADD_FAILED(400,"ADD_FAILED","添加失败"),
    UPDATE_FAILED(400,"UPDATE_FAILED","修改失败"),
    DELETE_FAILED(400,"DELETE_FAILED","删除失败"),
    APPLY_CAMP_FAILED(400,"APPLY_CAMP_FAILED", "申请开班失败"),
    NOT_TEACHER_ERROR(400,"NOT_TEACHER_ERROR","用户不是认证教师"),
    NO_SUCH_CAMP(400,"NO_SUCH_CAMP","没有该训练营"),
    QUERY_CAMP_FAILED(400,"QUERY_CAMP_FAILED","查询训练营失败"),
    REGISTER_FAILED(400,"REGISTER_FAILED","注册失败，请稍后重试"),
    ;



    private int status;
    private String code, message;

    private ErrorCode(int status, String code, String message) {
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
