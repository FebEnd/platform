package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.CouponBase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface CouponBaseMapper {
    int add(CouponBase couponBase);
    int update(CouponBase couponBase);
    int deleteByIds(String[] ids);
    CouponBase findCouponBaseById(int id);
    List<CouponBase> findAllCouponBase();

}
