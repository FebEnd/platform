package com.platform.parent.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by tqyao.
 */
public class EnumUtil {
    public static Object errorToJson(ErrorCode errorCode) {
        return convert(errorCode.getStatus(),errorCode.getCode(),errorCode.getMessage());
    }

    public static Object succToJson(SuccessCode successCode) {
        return convert(successCode.getStatus(),successCode.getCode(),successCode.getMessage());
    }

    private static Object convert(int status, String code, String message) {
        JSONObject result = new JSONObject();
        result.put("status", status);
        result.put("code", code);
        result.put("message",message);
        return result;
    }
}
