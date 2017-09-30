package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Location;
import com.platform.parent.mybatis.dao.LocationMapper;
import com.platform.parent.mybatis.service.LocationService;
import com.platform.parent.response.location.CityWithDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    LocationMapper locationMapper;
    @Override
    public int add(Location location) {
        return this.locationMapper.add(location);
    }

    @Override
    public int update(Location location) {
        return this.locationMapper.update(location);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.locationMapper.deleteByIds(ids);
    }

    @Override
    public Location findLocationById(long id) {
        return this.locationMapper.findLocationById(id);
    }

    @Override
    public List<Location> findLocationByParams(Map<String, String> params) {
        return this.locationMapper.findLocationByParams(params);
    }

    @Override
    public List<CityWithDistrict> findCitiesWithDistrict(Map<String, String[]> params) {
        return this.locationMapper.findCitiesWithDistrict(params);
    }
}
