package com.platform.parent.util;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

/**
 * Created by tqyao.
 */
public class DateUtilTest {

    @Test
    public void testStringToSqlDate() throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String s = "201708";
        java.sql.Date date = DateUtil.stringToSqlDate(s);
//        java.sql.Date date = new java.sql.Date(format.parse("20170815").getTime());
//        java.util.Date date1 = format.parse("20170815");
//        System.out.println(date1);
//        System.out.println(format.parse("20170815"));
        System.out.println(date);
//        System.out.println(format.parse(s));
        Assert.assertEquals("2017-08-01", date.toString());
    }
}
