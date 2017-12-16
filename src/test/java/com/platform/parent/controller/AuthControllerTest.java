package com.platform.parent.controller;

import com.platform.parent.request.auth.LoginReq;
import com.platform.parent.request.auth.RegisterReq;
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
public class AuthControllerTest {

    @Autowired
    AuthController authController;


    @Test
    public void testLogin() {
//        AuthController authController = new AuthController();
        authController.getVerifyCode("15001877056");

        authController.login(new LoginReq("15001877056","1111"));
    }

    @Test
    public void testRegister() {
//        AuthController authController = new AuthController();
        authController.getVerifyCode("15000000012");

        authController.register(new RegisterReq("15000000012","1111","-1",1));
    }
}
