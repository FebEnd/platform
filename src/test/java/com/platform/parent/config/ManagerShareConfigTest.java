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
public class ManagerShareConfigTest {
    @Autowired
    ManagerShareConfig managerShareConfig;

    @Test
    public void testConfigRead() {
        Assert.assertEquals(1, managerShareConfig.getShare());
    }
}
