package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.CampDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tqyao.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CampDetailServiceTest {
    @Autowired
    CampDetailService service;

    @Test
    public void test() {
        CampDetail detail = new CampDetail();
        detail.setId(1);
        detail.setAnnouncement("test announcement test announcement test announcement test announcement");
        service.add(detail);
    }
}
