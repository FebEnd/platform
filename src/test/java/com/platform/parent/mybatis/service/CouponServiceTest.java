package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Coupon;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tqyao.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CouponServiceTest {
    @Autowired
    CouponService couponService;

    @Test
    public void testAdd() {
        Coupon coupon = new Coupon().name("新用户专属").description("新注册用户的专属优惠券").amount(new BigDecimal(100.00)).duration(30);
        int i = this.couponService.add(coupon);
        Assert.assertNotEquals(0,i);
    }

    @Test
    public void testUpdate() {
        Coupon coupon = new Coupon().id(1).name("新用户专属").description("新注册用户的专属优惠券").amount(new BigDecimal(100.00)).duration(50);
        int i = this.couponService.update(coupon);
        Assert.assertNotEquals(0,i);
    }

    @Test
    public void testDeleteByIds() {
        String[] ids = {"3","2"};
        int i = this.couponService.deleteByIds(ids);
        Assert.assertNotEquals(0,i);
    }

    @Test
    public void  testFindById() {
        long id = 1l;
        Coupon coupon = this.couponService.findCouponById(id);
        Assert.assertEquals(50, coupon.getDuration());
    }

    @Test
    public void testFindAllCoupons() {
        List<Coupon> coupons = this.couponService.findAllCoupons();
        Assert.assertNotEquals(0, coupons.size());
    }
}
