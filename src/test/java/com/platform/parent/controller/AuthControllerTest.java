package com.platform.parent.controller;

import com.platform.parent.request.auth.LoginReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tqyao.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthControllerTest {

    @Test
    public void testLogin() {
        AuthController authController = new AuthController();
        authController.getVerifyCode("15001877056");

        authController.login(new LoginReq("15001877056","1111"));
    }
}
