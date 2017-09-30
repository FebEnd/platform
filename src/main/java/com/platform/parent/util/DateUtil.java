package com.platform.parent.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by tqyao.
 */
public class DateUtil {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static java.sql.Date stringToSqlDate(String string) {
        try {
            return new java.sql.Date(format.parse(string).getTime());
        } catch (ParseException e) {
            logger.error(e.getMessage());
//            e.printStackTrace();
        }
        return null;
    }

    public static String sqlDateStringFormat(String s) {
        try {
            return new java.sql.Date(format.parse(s).getTime()).toString();
        } catch (ParseException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return s+"01";
    }

}
