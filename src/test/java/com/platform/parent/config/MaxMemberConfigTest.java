package com.platform.parent.config;

import com.platform.parent.PlatformApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tqyao.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MaxMemberConfigTest {
    @Autowired
    MaxMemberConfig memberConfig;

    @Test
    public void testConfigRead() {
        Assert.assertEquals(100, memberConfig.getOne());
        Assert.assertEquals(150, memberConfig.getTwo());
        Assert.assertEquals(200, memberConfig.getThree());
        Assert.assertEquals(300, memberConfig.getFour());
        Assert.assertEquals(500, memberConfig.getFive());
//        System.out.println(memberConfig.getOne());
    }

}
