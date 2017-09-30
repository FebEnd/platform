package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Location;
import com.platform.parent.response.location.CityWithDistrict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface LocationMapper {

    int add(Location location);
    int update(Location location);
    int deleteByIds(String[] ids);
    Location findLocationById(long id);
    List<Location> findLocationByParams(Map<String, String> params);
    List<CityWithDistrict> findCitiesWithDistrict(Map<String, String[]> params);

}
