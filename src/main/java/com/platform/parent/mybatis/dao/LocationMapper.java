package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Location;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by tqyao.
 */
@Mapper
public interface LocationMapper {

    int add(Location location);
    int update(Location location);
    int deleteByIds(String[] ids);
    Location findLocationById(long id);
    Location findLocationByParams(Map<String, String> params);

}
