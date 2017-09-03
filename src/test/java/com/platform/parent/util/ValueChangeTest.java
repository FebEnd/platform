package com.platform.parent.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tqyao.
 */
public class ValueChangeTest {
    @Test
    public void testLong() {
        String channel = "5236";
        long l = Long.valueOf(channel);
        if (l >0) {
            System.out.println(">0");
        }
        Assert.assertEquals(5236, l);
    }
}
