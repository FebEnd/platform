package com.platform.parent.util;

/**
 * Created by tqyao.
 */
public enum ErrorCode {
    KEYWORD_IS_NULL(1000,"KEYWORD_IS_NULL","关键词为空"),
    NO_SUCH_SCHOOL(1000,"NO_SUCH_SCHOOL","没有相关学校"),
    QUERY_SCHOOL_FAILED(1000,"QUERY_SCHOOL_FAILED","查询学校失败"),
    ADD_FAILED(1000,"ADD_FAILED","添加失败"),
    UPDATE_FAILED(1000,"UPDATE_FAILED","修改失败"),
    DELETE_FAILED(1000,"DELETE_FAILED","删除失败"),
    APPLY_CAMP_FAILED(1000,"APPLY_CAMP_FAILED", "申请开班失败"),
    NOT_TEACHER_ERROR(1000,"NOT_TEACHER_ERROR","用户不是认证教师"),
    NO_SUCH_CAMP(1000,"NO_SUCH_CAMP","没有该训练营"),
    QUERY_CAMP_FAILED(1000,"QUERY_CAMP_FAILED","查询训练营失败"),
    REGISTER_FAILED(1000,"REGISTER_FAILED","注册失败"),
    NO_SUCH_USER(1000,"NO_SUCH_USER","没有此用户"),
    NO_SUCH_COUPON(1000,"NO_SUCH_COUPON","优惠券信息错误"),
    QUERY_COUPON_ERROR(1000,"QUERY_COUPON_ERROR","优惠券信息查询错误"),
    NO_SUCH_PHONE(1000,"NO_SUCH_PHONE","没有该手机号"),
    VERIFY_CODE_ERROR(1000,"VERIFY_CODE_ERROR","验证码错误"),
    USER_ALREADY_EXISTS(1000,"USER_ALREADY_EXISTS","用户已存在"),
    MESSAGE_SEND_FAILED(1000,"MESSAGE_SEND_FAILED","验证码发送失败"),
    COMPLETE_INFO_FAILED(1000,"COMPLETE_INFO_FAILED","完善信息失败"),
    NO_SUCH_CITY(1000,"NO_SUCH_CITY","没有该城市信息"),
    ILLEGAL_USER_ID(1000,"ILLEGAL_USER_ID","非法的用户id"),
    APPLY_TEACHER_FAILED(1000,"APPLY_TEACHER_FAILED","申请认证教师失败"),
    APPLY_AUTH_FAILED(1000,"APPLY_AUTH_FAILED","申请实名认证失败"),
    ILLEGAL_REQUEST_PARAM(1000,"ILLEGAL_REQUEST_PARAM","非法请求参数"),
    QUERY_MEMBER_FAILED(1000,"QUERY_MEMBER_FAILED","查询成员失败"),
    QUERY_TEACHER_FAILED(1000,"QUERY_TEACHER_FAILED","查询导师失败"),
    QUERY_COLLECTION_FAILED(1000,"QUERY_COLLECTION_FAILED","查询收藏失败"),
    ADD_FAVOR_FAILED(1000,"ADD_FAVOR_FAILED","点赞失败"),
    CREATE_ORDER_FAILED(1000,"CREATE_PAYMENT_FAILED","创建订单失败"),
    NO_SUCH_ORDER(1000,"NO_SUCH_ORDER","订单不存在"),
    ORDER_ALREADY_CANCELED(1000,"ORDER_ALREADY_CANCELED","订单已取消"),
    COMPLETE_ORDER_FAILED(1000,"COMPLETE_ORDER_FAILED","完成订单失败"),
    CANCEL_ORDER_FAILED(1000, "CANCEL_ORDER_FAILED","取消订单失败"),
    ATTEND_CAMP_FAILED(1000,"ATTEND_CAMP_FAILED","加入训练营失败"),
    PURCHASE_VIP_FAILED(1000,"PURCHASE_VIP_FAILED","购买VIP失败"),
    NO_LOCATION_FOUND(1000,"NO_LOCATION_FOUND","没有找到任何城市"),
    USER_NOT_ATTEND_CAMP(1000,"USER_NOT_ATTEND_CAMP","用户并未加入该训练营"),
    CREATE_CHAT_GROUP_FAILED(1000,"CREATE_CHAT_GROUP_FAILED","创建群聊失败"),
    ATTEND_CHAT_GROUP_FAILED(1000,"ATTEND_CHAT_GROUP_FAILED","加入群聊失败"),
    TOPIC_NOT_EXIST(1000,"TOPIC_NOT_EXIST","话题不存在"),
    NO_AUTH_ERROR(1000,"NO_AUTH_ERROR", "用户无权限做此操作"),
    ADD_COLLECTION_FAILED(1000,"ADD_COLLECTION_FAILED","加入收藏失败"),
    ALREADY_IN_COLLECTION(1000,"ALREADY_IN_COLLECTION","已经在收藏中"),
    NO_AUTH_JOIN(1000, "NO_AUTH_JOIN", "无加入权限"),
    FINANCIAL_BAD_SETTLEMENT_REQUEST(5000,"FINANCIAL_BAD_SETTLEMENT_REQUEST","转账请求输入错误： {0}"),
    FINANCIAL_SETTLEMENT_REQUEST_NOT_CAMP_BASED(5001,"FINANCIAL_SETTLEMENT_REQUEST_NOT_CAMP_BASED","转账请求输入错误， 请输入训练营中的所有需要转账的导师或管理员"),
    FINANCIAL_CREATE_OR_UPDATE_USER_SETTLEMENT_FAILED(5002,"FINANCIAL_CREATE_OR_UPDATE_USER_SETTLEMENT_FAILED","创建或更新转账记录失败")
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
