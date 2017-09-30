package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.UserCoupon;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by tqyao.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserCouponServiceTest {
    @Autowired
    UserCouponService userCouponService;

    @Test
    public void testAdd() {
//        int i = this.userCouponService.add(1l,-1l);
        for (int i = 1; i<9;i++) {
            this.userCouponService.add(i,-1l);
        }
//        Assert.assertNotEquals(0,i);
    }

    @Test
    public void testFindUsable() {
        int count = this.userCouponService.findCountUsableByUserId(1l);
        Assert.assertNotEquals(0,count);
    }

    @Test
    public void testFindCouponByUserId() {
        List<UserCoupon> coupons = this.userCouponService.findUserCouponByUserId(1l);
        Assert.assertNotEquals(0,coupons.size());
    }

    @Test
    public void testFindCouponByUserIdAndCouponId() {
        List<UserCoupon> coupons = this.userCouponService.findUserCouponByCouponIdAndUserId(1l,1);
    }
}
