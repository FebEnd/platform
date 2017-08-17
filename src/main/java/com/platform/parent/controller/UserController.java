package com.platform.parent.controller;

import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.service.UserService;
import com.platform.parent.response.user.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/user")
@PreAuthorize(value = "hasAnyRole('USER', 'ADMIN')")
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping(value = "/completeInfo")
    public @ResponseBody Object completeInfo(@RequestBody User user) {
        int i = userService.update(user);
        if (i != 0) {
            return new MsgResponse("0", "完善资料成功");
        } else {
            return new MsgResponse("203","完善资料失败");
        }
    }

    @GetMapping(value = "/getDetail")
    public @ResponseBody Object getDetail(@RequestParam("id") long id) {
        Map<String, String> result = new HashMap<>();
        //todo 通过各个service获取所需信息并组装
        return result;
    }
}
