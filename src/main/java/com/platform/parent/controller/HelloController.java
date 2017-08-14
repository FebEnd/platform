package com.platform.parent.controller;

import com.platform.parent.config.MaxMemberConfig;
import com.platform.parent.response.hello.ConfigResponse;
import com.platform.parent.response.user.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    MaxMemberConfig memberConfig;

    @RequestMapping(value = "/testconfig", method = RequestMethod.GET)
    public @ResponseBody Object register() throws IOException {
        System.out.println(memberConfig.getFive());
        ConfigResponse response = new ConfigResponse(memberConfig.getOne(),memberConfig.getTwo(),memberConfig.getThree(),memberConfig.getFour(),memberConfig.getFive());
        return response;
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody Object testAuth() {
        return new MsgResponse("0","来自/test接口");
    }
}
