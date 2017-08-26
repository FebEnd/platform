package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.CouponStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface CouponStrategyMapper {
    int add(CouponStrategy couponStrategy);
    int update(CouponStrategy couponStrategy);
    int deleteByIds(String[] ids);
    List<CouponStrategy> findCouponStrategyByChannel(int channel);
    List<CouponStrategy> findAllCouponStrategy();
}
