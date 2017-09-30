package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.CouponStrategy;
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
public class CouponStragetyServiceTest {
    @Autowired
    CouponStrategyService strategyService;

    @Test
    public void testAdd() {
        CouponStrategy strategy = new CouponStrategy().baseId(1).channel(1).number(2);
        CouponStrategy strategy1 = new CouponStrategy().baseId(2).channel(1).number(1);
        CouponStrategy strategy2 = new CouponStrategy().baseId(3).channel(1).number(2);
        int i = this.strategyService.add(strategy);
        this.strategyService.add(strategy1);
        this.strategyService.add(strategy2);
        Assert.assertNotEquals(0,i);
    }

    @Test
    public void testUpdate() {
        CouponStrategy strategy = new CouponStrategy().baseId(1).channel(1).number(3).id(1);
        int i = this.strategyService.update(strategy);
        Assert.assertNotEquals(0,i);
    }

    @Test
    public void testDeleteByIds() {
        String[] ids = {"2","3"};
        int i = this.strategyService.deleteByIds(ids);
        Assert.assertNotEquals(0,i);
    }

    @Test
    public void testFindByChannel() {
        int channel = 1;
        List<CouponStrategy> strategies = this.strategyService.findCouponStrategyByChannel(channel);
        Assert.assertNotEquals(0,strategies.size());
    }

    @Test
    public void testFindAll() {
        List<CouponStrategy> strategies = this.strategyService.findAllCouponStrategy();
        Assert.assertNotEquals(0,strategies.size());
    }
}
