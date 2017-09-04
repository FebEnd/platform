package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.UserCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface UserCouponMapper {
    int add(UserCoupon userCoupon);
    int update(UserCoupon userCoupon);
    int deleteByIds(String[] ids);
    List<UserCoupon> findUserCouponByUserId(long userId);
    List<UserCoupon> findUserCouponByChannelAndUserId(@Param("userId") long userId, @Param("channel") long channel);
    List<UserCoupon> findUserCouponByCouponIdAndUserId(@Param("userId") long userId, @Param("couponId") int couponId);
//    int findCountByUserId(long userId);
    int findCountUsableByUserId(long userId);
}
