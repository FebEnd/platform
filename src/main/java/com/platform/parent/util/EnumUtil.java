package com.platform.parent.util;

import com.alibaba.fastjson.JSONObject;

import java.text.MessageFormat;

/**
 * Created by tqyao.
 */
public class EnumUtil {
    public static Object errorToJson(ErrorCode errorCode) {
        return convert(errorCode.getStatus(),errorCode.getCode(),errorCode.getMessage(), null);
    }

    public static Object errorToJson(ErrorCode errorCode, String details) {
        return convert(errorCode.getStatus(),errorCode.getCode(),errorCode.getMessage(), details);
    }

    public static Object succToJson(SuccessCode successCode) {
        return convert(successCode.getStatus(),successCode.getCode(),successCode.getMessage(), null);
    }

    private static Object convert(int status, String code, String message, String details) {
        JSONObject result = new JSONObject();
        result.put("status", status);
        result.put("code", code);
        String finalMessage = message;
        if (details != null)
        {
            MessageFormat form = new MessageFormat(message);
            finalMessage = form.format(new Object[]{details});
        }
        result.put("message",finalMessage);
        return result;
    }
}
