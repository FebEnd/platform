package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.config.MaxMemberConfig;
import com.platform.parent.response.hello.ConfigResponse;
import com.platform.parent.response.user.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/testData")
    public @ResponseBody Object testData() {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("id",1);
        data.put("nickname","nickname");
        result.put("status",0);
        result.put("data",data);
        result.put("message","获取成功");
        return result;
    }
}
