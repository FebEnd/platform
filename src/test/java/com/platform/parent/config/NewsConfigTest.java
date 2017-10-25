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
public class NewsConfigTest {
    @Autowired
    NewsConfig config;

    @Test
    public void testConfig() {
        String s = config.getName().get(11);
        String[] names = s.split("\t");
        System.out.println(s);
        Assert.assertEquals("Butterfly\tAlex", config.getName().get(0));
        Assert.assertEquals("Les yeux",names[0]);
        Assert.assertEquals("熊猫笨笨33",names[1]);
    }
}
