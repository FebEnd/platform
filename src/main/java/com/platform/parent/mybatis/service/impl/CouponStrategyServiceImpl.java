package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.CouponStrategy;
import com.platform.parent.mybatis.dao.CouponStrategyMapper;
import com.platform.parent.mybatis.service.CouponStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class CouponStrategyServiceImpl implements CouponStrategyService {
    @Autowired
    CouponStrategyMapper mapper;

    @Override
    public int add(CouponStrategy couponStrategy) {
        return this.mapper.add(couponStrategy);
    }

    @Override
    public int update(CouponStrategy couponStrategy) {
        return this.mapper.update(couponStrategy);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.mapper.deleteByIds(ids);
    }

    @Override
    public List<CouponStrategy> findCouponStrategyByChannel(int channel) {
        return this.mapper.findCouponStrategyByChannel(channel);
    }

    @Override
    public List<CouponStrategy> findAllCouponStrategy() {
        return this.mapper.findAllCouponStrategy();
    }
}
