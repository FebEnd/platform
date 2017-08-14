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
public class TeacherShareConfigTest {
    @Autowired
    TeacherShareConfig shareConfig;

    @Test
    public void testConfigRead() {
        Assert.assertEquals(70, shareConfig.getOne());
        Assert.assertEquals(75, shareConfig.getTwo());
        Assert.assertEquals(80, shareConfig.getThree());
        Assert.assertEquals(83, shareConfig.getFour());
        Assert.assertEquals(85, shareConfig.getFive());
    }
}
