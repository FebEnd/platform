package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Coupon;
import com.platform.parent.mybatis.bean.CouponStrategy;
import com.platform.parent.mybatis.bean.UserCoupon;
import com.platform.parent.mybatis.dao.CouponMapper;
import com.platform.parent.mybatis.dao.CouponStrategyMapper;
import com.platform.parent.mybatis.dao.UserCouponMapper;
import com.platform.parent.mybatis.service.UserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class UserCouponServiceImpl implements UserCouponService {
    @Autowired
    UserCouponMapper userCouponMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponStrategyMapper couponStrategyMapper;

    @Override
    public int add(long userId, long channel) {
        int type = 0;
        if (channel == -1l) {
            //自然
            type = 1;
        } else if (channel == -2) {
            //学校渠道
            type = 0;
        } else {
            //用户推荐
            type = 2;
        }
        /*List<CouponStrategy> strategies = this.couponStrategyMapper.findCouponStrategyByChannel(type);
        Timestamp publish = new Timestamp(System.currentTimeMillis());
        boolean suc = true;
        for (CouponStrategy strategy : strategies) {
            Coupon coupon = this.couponMapper.findCouponById(strategy.getBaseId());
            int number = strategy.getNumber();
            long couponId = coupon.getId();
            Timestamp expiration = new Timestamp(publish.getTime() + coupon.getDuration()*24*3600*1000);
            for (int i = 0; i < number; i++) {
                UserCoupon userCoupon = new UserCoupon().userId(userId).couponId(coupon.getId())
                        .publish(publish).expiration(expiration);
                int j = this.userCouponMapper.add(userCoupon);
                suc = suc &&(j > 0);
            }
        }
        return suc ? 1 : 0;*/
        return publishAward(userId, type);
    }

    @Override
    public int publishAward(long userId) {
        /*List<CouponStrategy> strategies = this.couponStrategyMapper.findCouponStrategyByChannel(3);
        Timestamp publish = new Timestamp(System.currentTimeMillis());
        boolean suc = true;
        for (CouponStrategy strategy : strategies) {
            Coupon coupon = this.couponMapper.findCouponById(strategy.getBaseId());
            int number = strategy.getNumber();
            long couponId = coupon.getId();
            Timestamp expiration = new Timestamp(publish.getTime() + coupon.getDuration()*24*3600*1000);
            for (int i = 0; i < number; i++) {
                UserCoupon userCoupon = new UserCoupon().userId(userId).couponId(coupon.getId())
                        .publish(publish).expiration(expiration);
                int j = this.userCouponMapper.add(userCoupon);
                suc = suc &&(j > 0);
            }
        }
        return suc ? 1 : 0;*/
        return publishAward(userId, 3);
    }

    private int publishAward(long userId, int type) {
        List<CouponStrategy> strategies = this.couponStrategyMapper.findCouponStrategyByChannel(type);
        Timestamp publish = new Timestamp(System.currentTimeMillis());
        boolean suc = true;
        System.out.println(strategies.size());
        for (CouponStrategy strategy : strategies) {
            System.out.println(strategy.getBaseId());
            System.out.println(strategy.getNumber());
            System.out.println(strategy.getChannel());
            Coupon coupon = this.couponMapper.findCouponById(strategy.getBaseId());
            int number = strategy.getNumber();
            int couponId = coupon.getId();
            Timestamp expiration = new Timestamp(publish.getTime() + coupon.getDuration()*24*3600*1000);
            for (int i = 0; i < number; i++) {
                UserCoupon userCoupon = new UserCoupon().userId(userId).couponId(coupon.getId())
                        .publish(publish).expiration(expiration);
                int j = this.userCouponMapper.add(userCoupon);
                suc = suc &&(j > 0);
            }
        }
        return suc ? 1 : 0;
    }

    @Override
    public int update(UserCoupon userCoupon) {
        return this.userCouponMapper.add(userCoupon);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.userCouponMapper.deleteByIds(ids);
    }

    @Override
    public List<UserCoupon> findUserCouponByUserId(long userId) {
        return this.userCouponMapper.findUserCouponByUserId(userId);
    }

    @Override
    public List<UserCoupon> findUserCouponByCouponIdAndUserId(long userId, int couponId) {
        return this.userCouponMapper.findUserCouponByCouponIdAndUserId(userId, couponId);
    }

    @Override
    public int findCountUsableByUserId(long userId) {
        return this.userCouponMapper.findCountUsableByUserId(userId);
    }

    @Override
    public int setUserCouponUsedByIds(String[] ids) {
        List<UserCoupon> userCoupons = this.userCouponMapper.findUserCouponByIds(ids);
        if (userCoupons == null || userCoupons.size() < ids.length) {
            return 0;
        }
        for (UserCoupon userCoupon : userCoupons) {
            if (userCoupon.isUsed()) {
                return 0;
            }
        }
        return this.userCouponMapper.setUserCouponUsedByIds(ids);
    }
}
