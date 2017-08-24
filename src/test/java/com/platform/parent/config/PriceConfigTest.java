package com.platform.parent.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tqyao.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PriceConfigTest {
    @Autowired
    PriceConfig priceConfig;

    @Test
    public void testConfig() {
        Assert.assertEquals("0301", priceConfig.getSprint().get("start"));
        Assert.assertEquals("0505", priceConfig.getSprint().get("end"));
        Assert.assertEquals("2", priceConfig.getSprint().get("rate"));
        Assert.assertEquals("100-200", priceConfig.getOrdinary().get("one"));
        Assert.assertEquals("150-250", priceConfig.getOrdinary().get("two"));
        Assert.assertEquals("200-350", priceConfig.getOrdinary().get("three"));
        Assert.assertEquals("250-400", priceConfig.getOrdinary().get("four"));
        Assert.assertEquals("300-600", priceConfig.getOrdinary().get("five"));
    }
}
