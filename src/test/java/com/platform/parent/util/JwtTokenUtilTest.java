package com.platform.parent.util;

import com.platform.parent.config.JwtConfig;
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
public class JwtTokenUtilTest {
    @Autowired
    JwtTokenUtil tokenUtil;
    @Autowired
    JwtConfig config;

    @Test
    public void testGenerateDate() {
        System.out.println(tokenUtil.expiration);
        System.out.println(tokenUtil.generateToken("15001877058","password"));
        System.out.println(config.getExpiration());

    }
}
