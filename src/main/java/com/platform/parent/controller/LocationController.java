package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.mybatis.service.LocationService;
import com.platform.parent.response.location.CityWithDistrict;
import com.platform.parent.util.EnumUtil;
import com.platform.parent.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/getCityList", method = RequestMethod.POST)
    @ResponseBody
    public Object getCityList(@RequestParam(required = false, value = "province") String province,
                              @RequestParam(required = false, value = "city") String city) {
        Map<String, String[]> params = new HashMap<>();
        if (province != null) {
            String[] tokens = province.trim().split(",");
            params.put("province", tokens);
        }
        if (city != null) {
            String[] tokens = city.trim().split(",");
            params.put("city", tokens);
        }
        List<CityWithDistrict> cities = this.locationService.findCitiesWithDistrict(params);
        if (cities == null || cities.size() == 0) {
            return EnumUtil.errorToJson(ErrorCode.NO_LOCATION_FOUND);
        }
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status",200);
        result.put("message","成功");
        data.put("cities", cities);
        result.put("data",data);
        return result;
    }
}
