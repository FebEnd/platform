package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.UserCoupon;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface UserCouponService {
    int add(long userId, long channel);
    int publishAward(long userId);
    int update(UserCoupon userCoupon);
    int deleteByIds(String[] ids);
    List<UserCoupon> findUserCouponByUserId(long userId);
//    List<UserCoupon> findUserCouponByChannelAndUserId(long userId, long channel);
    List<UserCoupon> findUserCouponByCouponIdAndUserId(long userId, int couponId);
    //    int findCountByUserId(long userId);
    int findCountUsableByUserId(long userId);
    int setUserCouponUsedByIds(String[] ids);
}
