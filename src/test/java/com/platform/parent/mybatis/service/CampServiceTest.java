package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Camp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 * * id INT(11) NOT NULL AUTO_INCREMENT,
 * type INT(11) DEFAULT 0, /*0 家长创建， 1 专项， 2 机构
 * favor INT(11) DEFAULT 0,/*点赞数
 * max_limit INT(11) DEFAULT 0,/*最大人数限制
 * min_limit INT(11) DEFAULT 0,/*最小人数限制
 * status INT(11) DEFAULT 0, /*0 初始, 1 上线, 2 开课, 3 下线
 * price0 DECIMAL(10,2),
 * price1 DECIMAL(10,2),
 * price2 DECIMAL(10,2),
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CampServiceTest {
    @Autowired
    CampService service;

    @Test
    public void testAdd() {
        Camp camp = new Camp(0, 5, 0, 0, 0, new BigDecimal(100.00), new BigDecimal(150.00), new BigDecimal(200.00));
        int i = service.add(camp);
        Assert.assertNotEquals(0, i);
    }

    @Test
    public void testUpdate() {
        Camp camp = new Camp(1, 0, 5, 100, 0, 0, new BigDecimal(100.00), new BigDecimal(150.00), new BigDecimal(200.00));
        int i = service.update(camp);
        Assert.assertNotEquals(0, i);
    }

    @Test
    public void testDelete() {
        String[] ids = {"1", "2"};
        int i = service.deleteByIds(ids);
        Assert.assertNotEquals(0, i);
    }

    /**
     * int add(Camp camp);
     * int update(Camp camp);
     * int deleteByIds(String[] ids);
     * Camp queryCampById(long id);
     * List<Camp> findCampsByType(int type);
     * List<Camp> findCampsByStatus(int status);
     * List<Camp> findCampsByParams(Map<String, Object> params);
     */

    @Test
    public void testQueryCampById() {
        Camp camp = service.queryCampById(4);
        System.out.println(camp.getFavor());
        Assert.assertEquals(5,camp.getFavor());
    }

    @Test
    public void tsetFindCampsByType() {
        int type = 0;
        List<Camp> list = service.findCampsByType(type);
        Assert.assertEquals(2,list.size());
    }

    @Test
    public void testFindCampsByStatus() {
        int status = 0;
        List<Camp> list = service.findCampsByStatus(status);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testFindCampsByParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("type", 0);
//        params.put("status", 1);
        params.put("maxLimit", 500);
        List<Camp> list = service.findCampsByParams(params);
        Assert.assertEquals(1, list.size());
    }
}
