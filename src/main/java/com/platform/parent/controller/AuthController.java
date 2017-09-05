package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.platform.parent.easemob.api.IMUserAPI;
import com.platform.parent.easemob.api.impl.EasemobIMUser;
import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.service.UserCouponService;
import com.platform.parent.mybatis.service.UserService;
import com.platform.parent.request.auth.LoginReq;
import com.platform.parent.request.auth.RegisterReq;
import com.platform.parent.util.*;
import io.swagger.client.model.RegisterUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tqyao.
 * controller for user operation
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    //阿里云服务签名参数
    static final String signName = "姚天麒";
    //阿里云服务模板code
    static final String templateCode = "SMS_82030073";
    static Map<String, String> verifyMap = new HashMap<String, String>();
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private static final IMUserAPI imUser = new EasemobIMUser();

    @Autowired
    static JwtTokenUtil tokenUtil = new JwtTokenUtil();
    @Autowired
    UserService userService;
    @Autowired
    UserCouponService userCouponService;

    /**
     * 请求短信验证码接口，按照阿里云服务规则，每60s可以发送一条，每1h可以发送5条
     *
     * @param phone 请求验证码的手机号
     * @return 验证码是否发送成功
     */
    @RequestMapping(value = "/getVerify", method = RequestMethod.GET)
    public @ResponseBody
    Object getVerifyCode(@RequestParam("phone") String phone) {
        //默认前端已做60s限定，即，一但发送请求均视为距上一次请求验证码已过60s
        //判断目前服务端是否已对目标手机号发送过验证码，是则移除储存的验证码
        User user = this.userService.findUserByPhone(phone);
        boolean exists = false;
        if (user != null) {
            //用户已存在
            exists = true;
        } else {
            exists = false;
        }
        if (verifyMap.containsKey(phone)) {
            verifyMap.remove(phone);
        }
        //获取6位数字码
        System.out.println(phone);
        String number = VerificationCodeUtil.getRandNum(6);
        //验证码模板参数，与阿里云服务平台申请的模板保持一致
        String templateParam = "{\"number\" : \" " + number + "\"}";
        //尝试发送验证码短信
        try {
            MsgUtil.sendSms(phone, signName, templateCode, templateParam);
        } catch (ClientException e) {//阿里云服务故障
            logger.error(e.getMessage());
//            e.printStackTrace();
            return EnumUtil.errorToJson(ErrorCode.MESSAGE_SEND_FAILED);
        }
        //验证码发送成功
        //将验证码存入map
        verifyMap.put(phone, "1111");
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("exists", exists);
        result.put("status", 0);
        result.put("message", "短信发送成功");
        result.put("data", data);
        return result;
    }

    @PostMapping(value = "/register")
    public @ResponseBody Object register(RegisterReq req) {
        User user = this.userService.findUserByPhone(req.getPhone());
        if (user != null) {
            login(new LoginReq(req.getPhone(),req.getNumber()));
        }
        if (!verifyMap.containsKey(req.getPhone())){
            //服务器不存在该手机号申请验证记录
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_PHONE);
        } else {
            if (verify(req.getPhone(),req.getNumber())) {
                //验证码正确
                //注册新的环信账号，用户名为手机号，密码由UserUtil生成
                String password = UserUtil.generatePassword(12);
                long referee = Long.valueOf(req.getChannel());
                User user1 = new User().phone(req.getPhone()).password(password).nickname(req.getPhone());
                if (referee > 0) {
                    //有用户推荐人
                    user1 = user1.referee(referee);
                    this.userCouponService.publishAward(referee);
                } else {
                    //没有用户推荐人
                    user1 = user1.referee(0l);
                }
                long i = userService.add(user1);
                //todo 发放优惠券
                int index = this.userCouponService.add(user1.getId(), Long.valueOf(req.getChannel()));
                if (i > 0 ) {
                    //add successfully
                    String response = (String) imUser.createNewIMUserSingle(generateRegisterUser(req.getPhone(), password));
                    //todo 解析返回的response
//                    JSONArray array = JSONArray.parseArray(response);
                    if (response != null) {
                        //注册成功
                        String token = tokenUtil.generateToken(req.getPhone(), password, user1.getId());
                        return succ(token);
                    } else {
                        //注册失败
                        logger.error("register easemob user failed");
                        return EnumUtil.errorToJson(ErrorCode.REGISTER_FAILED);
                    }
                } else {
                    //添加失败
                    logger.error("insert user into database failed.");
                    return EnumUtil.errorToJson(ErrorCode.REGISTER_FAILED);
                }
            } else {
                return EnumUtil.errorToJson(ErrorCode.VERIFY_CODE_ERROR);
            }
        }
    }

    /**
     * 登陆与注册接口
     * 如果数据库已存在该手机号则让用户登陆
     * 如果数据库未存在该手机号则将手机号录入数据库,申请环信云账号并让用户登陆
     *
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Object login(LoginReq req) {
        if (!verifyMap.containsKey(req.getPhone())) {
            //服务器不存在该手机号申请验证记录
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_PHONE);
        } else {
            //存在手机号申请验证记录
            //todo 查询数据库是否存在该手机号并执行相应操作
            User user = userService.findUserByPhone(req.getPhone());
            if (user != null) {
                //已有该用户不操作
                if (verify(req.getPhone(), req.getNumber())) {
                    //验证码正确
                    //todo 返回密码
                    String token = tokenUtil.generateToken(req.getPhone(), user.getPassword(), user.getId());
                    return succ(token);
                } else {
                    return EnumUtil.errorToJson(ErrorCode.VERIFY_CODE_ERROR);
                }
            } else {
                //没有该用户
                return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
            }
        }
    }

    private Object succ(String token) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("token", token);
        result.put("status",0);
        result.put("message","登录成功");
        result.put("data", data);
        return result;
    }

    private boolean verify(String phone, String number) {
        String code = verifyMap.get(phone);
        if (code.equals(number)) {
            //验证成功
            System.out.println(number);
            return true;
        } else {
            return false;
        }
    }

    /*private RegisterUsers generateRegisterUsers(Map<String, String> map) {
        RegisterUsers users = new RegisterUsers();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            io.swagger.client.model.User user = new io.swagger.client.model.User().username(entry.getKey()).password(entry.getValue());
            users.add(user);
        }
        return users;
    }*/

    private RegisterUsers generateRegisterUser(String username, String password) {
        RegisterUsers users = new RegisterUsers();
        users.add(new io.swagger.client.model.User().username(username).password(password));
        return users;
    }


}
