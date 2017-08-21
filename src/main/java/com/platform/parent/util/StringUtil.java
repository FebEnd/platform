package com.platform.parent.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tqyao.
 */
public class StringUtil {
    /**
     * 判断字符串是否为null、“ ”、“null”
     * @param obj
     * @return
     */
    public static boolean isNull(String obj) {
        if (obj == null){
            return true;
        }else if (obj.toString().trim().equals("")){
            return true;
        }else if(obj.toString().trim().toLowerCase().equals("null")){
            return true;
        }

        return false;
    }

    /**
     * 正则验证是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[+-]?[0-9]+[0-9]*(\\.[0-9]+)?");
        Matcher match = pattern.matcher(str);

        return match.matches();
    }
}
