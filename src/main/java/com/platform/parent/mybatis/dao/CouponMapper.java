package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface CouponMapper {
    int add(Coupon coupon);
    int update(Coupon coupon);
    int deleteByIds(String[] ids);
    Coupon findCouponById(long id);
    List<Coupon> findAllCoupons();
}
