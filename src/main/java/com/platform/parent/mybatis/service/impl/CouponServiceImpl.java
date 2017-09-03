package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Coupon;
import com.platform.parent.mybatis.dao.CouponMapper;
import com.platform.parent.mybatis.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponMapper couponMapper;
    @Override
    public int add(Coupon coupon) {
        return this.couponMapper.add(coupon);
    }

    @Override
    public int update(Coupon coupon) {
        return this.couponMapper.update(coupon);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.couponMapper.deleteByIds(ids);
    }

    @Override
    public Coupon findCouponById(long id) {
        return this.couponMapper.findCouponById(id);
    }

    @Override
    public List<Coupon> findAllCoupons() {
        return this.couponMapper.findAllCoupons();
    }
}
