package com.platform.parent.mybatis.service;

import com.platform.parent.response.location.CityWithDistrict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocationServiceTest {
    @Autowired
    LocationService locationService;

    @Test
    public void testFindCitiesWithDistrict() {
        Map<String, String[]> params = new HashMap<>();
        String[] pro = {"浙江省"};
        String[] cit = {",","杭州市","北京市"};
        params.put("city", cit);
        params.put("province", pro);
        List<CityWithDistrict> cities = this.locationService.findCitiesWithDistrict(params);
        for (CityWithDistrict city : cities) {
            System.out.println(city.getCity());
            System.out.println(city.getDistrict());
            System.out.println("=======================");
        }
    }

    /*@Test
    public void testFindLocationByParams() {
        Map<String, String> params = new HashMap<>();
        List<>
    }*/
}
