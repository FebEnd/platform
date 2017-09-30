package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.mybatis.bean.Coupon;
import com.platform.parent.mybatis.bean.UserCoupon;
import com.platform.parent.mybatis.service.CouponService;
import com.platform.parent.mybatis.service.UserCouponService;
import com.platform.parent.response.coupon.UserCouponRes;
import com.platform.parent.util.EnumUtil;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/coupon")
public class CouponController {

    @Autowired
    UserCouponService userCouponService;
    @Autowired
    CouponService couponService;
    /*@Autowired
    UserService userService;*/


    @GetMapping(value = "/listCoupons")
    public @ResponseBody Object listCoupons(@RequestParam("id") String id) {
//        User user = this.userService.queryUserById(Long.valueOf(id));
        if (!StringUtil.isNumber(id)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(id);
        List<UserCoupon> userCoupons = this.userCouponService.findUserCouponByUserId(userId);
        List<UserCouponRes> coupons = new ArrayList<>();
        if (userCoupons != null && userCoupons.size() > 0) {
            for (UserCoupon userCoupon : userCoupons) {
                UserCouponRes res;
                Coupon coupon = this.couponService.findCouponById(userCoupon.getCouponId());
                System.out.println(userCoupon.getCouponId());
                if (coupon != null) {
                    res = new UserCouponRes(coupon.getName(),coupon.getDescription(),coupon.getAmount(),userCoupon.getPublish(),userCoupon.getExpiration(),userCoupon.isUsed());
                    coupons.add(res);
                } /*else {
//                    return EnumUtil.errorToJson(ErrorCode.NO_SUCH_COUPON);
                }*/
            }
        } else {
            return EnumUtil.errorToJson(ErrorCode.QUERY_COUPON_ERROR);
        }
        /*UserCouponRes res = new UserCouponRes("新用户优惠券","新用户专属优惠券",new BigDecimal(200.00), Timestamp.valueOf("2017-08-26 19:58:45"), Timestamp.valueOf("2017-09-25 19:58:45"));
        UserCouponRes res1 = new UserCouponRes("老用户优惠券","老用户推荐奖励优惠券",new BigDecimal(200.00), Timestamp.valueOf("2017-08-26 19:58:45"), Timestamp.valueOf("2017-09-25 19:58:45"));
        UserCouponRes res2 = new UserCouponRes("国庆优惠券","国庆节日奖励优惠券",new BigDecimal(200.00), Timestamp.valueOf("2017-10-01 00:00:00"), Timestamp.valueOf("2017-11-01 00:00:00"));

        coupons.add(res);
        coupons.add(res1);
        coupons.add(res2);*/
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("coupons", coupons);
        result.put("data", data);
        result.put("status", 200);
        result.put("message", "成功");
        return result;
    }
}
