package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Camp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface CampMapper {
    int add(Camp camp);
    int update(Camp camp);
    int deleteByIds(String[] ids);
    Camp queryCampById(long id);
    List<Camp> findCampsByType(int type);
    List<Camp> findCampsByStatus(int status);0
}
