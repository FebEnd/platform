package com.platform.parent.controller;

import com.platform.parent.mybatis.bean.User;
import com.platform.parent.response.user.MsgResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/user")
@PreAuthorize(value = "hasAnyRole('USER', 'ADMIN')")
public class UserController {

    @PostMapping(value = "/completeInfo")
    public @ResponseBody Object completeInfo(@RequestBody User user) {
        return new MsgResponse("203","完善资料失败");
    }
}
