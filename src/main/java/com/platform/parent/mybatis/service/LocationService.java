package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Location;

import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
public interface LocationService {
    int add(Location location);
    int update(Location location);
    int deleteByIds(String[] ids);
    Location findLocationById(long id);
    List<Location> findLocationByParams(Map<String, String> params);

}
