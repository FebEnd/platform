package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.config.MaxMemberConfig;
import com.platform.parent.response.hello.ConfigResponse;
import com.platform.parent.response.user.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return new MsgResponse("200","来自/test接口");
    }

    @RequestMapping(value = "/testData", method = RequestMethod.POST)
    public @ResponseBody Object testData(@RequestParam("id") String id) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        Map<String, Object> map = new HashMap<>();
        map.put("three", 200);
        data.put("id",1);
        data.put("nickname","nickname");
        data.put("price",map);
        result.put("status",200);
        result.put("data",data);
        result.put("message","获取成功");
        return result;
    }

    @RequestMapping(value = "/testList", method = RequestMethod.GET)
    @ResponseBody
    public Object testList() {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        List<String> s = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            s.add("BlueFirefly 加入了 肥萌cat66 开办的训练营");
        }
        data.put("news", s);
        result.put("status", 0);
        result.put("message", "成功");
        result.put("data",data);
        return result;
    }

    @PostMapping(value = "/testNoData")
    public @ResponseBody Object testNoData() {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status",200);
        result.put("message", "成功");
        result.put("data",data);
        return result;
    }
}
