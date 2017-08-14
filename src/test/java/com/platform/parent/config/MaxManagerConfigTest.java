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
public class MaxManagerConfigTest {
    @Autowired
    MaxManagerConfig managerConfig;

    @Test
    public void testConfigRead() {
        Assert.assertEquals(3, managerConfig.getOne());
        Assert.assertEquals(3, managerConfig.getTwo());
        Assert.assertEquals(3, managerConfig.getThree());
        Assert.assertEquals(4, managerConfig.getFour());
        Assert.assertEquals(5, managerConfig.getFive());
    }
}
