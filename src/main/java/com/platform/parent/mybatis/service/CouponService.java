package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Coupon;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface CouponService {
    int add(Coupon coupon);
    int update(Coupon coupon);
    int deleteByIds(String[] ids);
    Coupon findCouponById(long id);
    List<Coupon> findCouponsByUserId(long userId);
    List<Coupon> findCouponsByChannel(long channel);
}
