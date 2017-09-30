package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Order;
import com.platform.parent.mybatis.bean.UserCoupon;
import com.platform.parent.mybatis.dao.OrderMapper;
import com.platform.parent.mybatis.dao.UserCouponMapper;
import com.platform.parent.mybatis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserCouponMapper userCouponMapper;

    @Override
    public int add(Order order) {
        if (order.getCoupons() != null && !order.getCoupons().trim().equals("")) {
            String[] ids = order.getCoupons().split(",");
            List<UserCoupon> userCoupons = this.userCouponMapper.findUserCouponByIds(ids);
            if (userCoupons == null || userCoupons.size() < ids.length) {
                return 0;
            }
            for (UserCoupon userCoupon : userCoupons) {
                if (userCoupon.isUsed()) {
                    return 0;
                }
            }
            //todo 锁定优惠券
            int i = this.userCouponMapper.setUserCouponUsedByIds(ids);
            if (i <= 0) {
                return 0;
            }
        }
        return this.orderMapper.add(order);
    }

    @Override
    public int update(Order order) {
        return this.orderMapper.update(order);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.orderMapper.deleteByIds(ids);
    }

    @Override
    public Order findOrderById(long id) {
        return this.orderMapper.findOrderById(id);
    }


    @Override
    public int cancelOrder(Order order) {
        if (order.getCoupons() != null && !order.getCoupons().trim().equals("")) {
            String[] ids = order.getCoupons().split(",");
            List<UserCoupon> userCoupons = this.userCouponMapper.findUserCouponByIds(ids);
            if (userCoupons == null || userCoupons.size() < ids.length) {
                return 0;
            }
            //todo 解锁优惠券
            int i = this.userCouponMapper.setUserCouponUnusedByIds(ids);
            if (i <= 0) {
                return 0;
            }
        }
        return this.orderMapper.update(order);
    }

    @Override
    public List<Order> findCompleteOrderForCamp() {
        return this.orderMapper.findCompleteOrderForCamp();
    }


    @Override
    public List<Order> findOrderByTime(Timestamp start, Timestamp end) {
        return this.orderMapper.findOrderByTime(start, end);
    }

    @Override
    public List<Order> findOrderByType(long type) {
        return this.orderMapper.findOrderByType(type);
    }
}
