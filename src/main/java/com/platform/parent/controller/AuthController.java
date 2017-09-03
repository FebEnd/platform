package com.platform.parent.controller;

import com.aliyuncs.exceptions.ClientException;
import com.platform.parent.easemob.api.IMUserAPI;
import com.platform.parent.easemob.api.impl.EasemobIMUser;
import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.service.UserCouponService;
import com.platform.parent.mybatis.service.UserService;
import com.platform.parent.response.user.LoginResponse;
import com.platform.parent.response.user.MsgResponse;
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
            return new MsgResponse("400", "短信服务故障");
        }
        //验证码发送成功
        //将验证码存入map
        verifyMap.put(phone, number);
        return new MsgResponse("0", "短信发送成功");
    }

    /**
     * 登陆与注册接口
     * 如果数据库已存在该手机号则让用户登陆
     * 如果数据库未存在该手机号则将手机号录入数据库,申请环信云账号并让用户登陆
     *
     * @param phone  要验证的手机号
     * @param number 用户输入的验证码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Object login(@RequestParam("phone") String phone, @RequestParam("number") String number, @RequestParam("channel") String channel) {
        if (!verifyMap.containsKey(phone)) {
            //服务器不存在该手机号申请验证记录
            return new MsgResponse("400", "手机号不存在");
        } else {
            //存在手机号申请验证记录
            //todo 查询数据库是否存在该手机号并执行相应操作
            User user = userService.findUserByPhone(phone);
            if (user != null) {
                //已有该用户不操作
                if (verify(phone, number)) {
                    //验证码正确
                    //todo 返回密码
                    String token = tokenUtil.generateToken(phone, user.getPassword(), user.getId());
                    return new LoginResponse("0", token);
                } else {
                    return new LoginResponse("400", "验证码错误");
                }
            } else {
                //没有该用户
                if (verify(phone, number)) {
                    //验证码正确
                    //注册新的环信账号，用户名为手机号，密码由UserUtil生成
                    String password = UserUtil.generatePassword(12);
                    long referee = Long.valueOf(channel);
                    User user1 = new User().phone(phone).password(password).nickname(phone);
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
                    int index = this.userCouponService.add(user1.getId(), Long.valueOf(channel));
                    if (i > 0 ) {
                        //add successfully
                        String response = (String) imUser.createNewIMUserSingle(generateRegisterUser(phone, password));
                        //todo 解析返回的response
                        if (response != null) {
                            //注册成功
                            String token = tokenUtil.generateToken(phone, password, user1.getId());
                            return new LoginResponse("0", token);
                        } else {
                            //注册失败
                            logger.error("register easemob user failed");
                            return new MsgResponse("400", "注册失败，请稍后重试");
                        }
                    } else {
                        //添加失败
                        logger.error("insert user into database failed.");
                        return new MsgResponse("400", "注册失败，请稍后重试");
                    }
                } else {
                    return new LoginResponse("400", "验证码错误");
                }
            }
        }
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
