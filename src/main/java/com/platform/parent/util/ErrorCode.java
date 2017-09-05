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
    REGISTER_FAILED(400,"REGISTER_FAILED","注册失败"),
    NO_SUCH_USER(400,"NO_SUCH_USER","没有此用户"),
    NO_SUCH_COUPON(400,"NO_SUCH_COUPON","优惠券信息错误"),
    QUERY_COUPON_ERROR(400,"QUERY_COUPON_ERROR","优惠券信息查询错误"),
    NO_SUCH_PHONE(400,"NO_SUCH_PHONE","没有该手机号"),
    VERIFY_CODE_ERROR(400,"VERIFY_CODE_ERROR","验证码错误"),
    USER_ALREADY_EXISTS(400,"USER_ALREADY_EXISTS","用户已存在"),
    MESSAGE_SEND_FAILED(400,"MESSAGE_SEND_FAILED","验证码发送失败"),
    COMPLETE_INFO_FAILED(400,"COMPLETE_INFO_FAILED","完善信息失败"),
    NO_SUCH_CITY(400,"NO_SUCH_CITY","没有该城市信息"),
    ILLEGAL_USER_ID(400,"ILLEGAL_USER_ID","非法的用户id"),
    APPLY_TEACHER_FAILED(400,"APPLY_TEACHER_FAILED","申请认证教师失败"),
    APPLY_AUTH_FAILED(400,"APPLY_AUTH_FAILED","申请实名认证失败"),
    ILLEGAL_REQUEST_PARAM(400,"ILLEGAL_REQUEST_PARAM","非法请求参数"),
    QUERY_MEMBER_FAILED(400,"QUERY_MEMBER_FAILED","查询成员失败"),
    QUERY_TEACHER_FAILED(400,"QUERY_TEACHER_FAILED","查询导师失败"),
    QUERY_COLLECTION_FAILED(400,"QUERY_COLLECTION_FAILED","查询收藏失败"),
    ADD_FAVOR_FAILED(400,"ADD_FAVOR_FAILED","点赞失败")
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
