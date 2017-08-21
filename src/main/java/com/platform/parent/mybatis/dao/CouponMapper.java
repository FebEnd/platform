package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Coupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
public interface CouponMapper {
    int add(Coupon coupon);
    int update(Coupon coupon);
    int deleteByIds(String[] ids);
    Coupon findCouponById(long id);
    List<Coupon> findCouponsByUserId(long userId);

}
