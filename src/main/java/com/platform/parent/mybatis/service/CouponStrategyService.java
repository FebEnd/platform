package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.CouponStrategy;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface CouponStrategyService {
    int add(CouponStrategy couponStrategy);
    int update(CouponStrategy couponStrategy);
    int deleteByIds(String[] ids);
    List<CouponStrategy> findCouponStrategyByChannel(int channel);
    List<CouponStrategy> findAllCouponStrategy();
}
