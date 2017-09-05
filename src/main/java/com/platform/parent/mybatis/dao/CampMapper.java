package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Camp;
import com.platform.parent.response.camp.CampList;
import com.platform.parent.response.camp.CampWithTeacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    Camp findCampByTeacherId(long id);
    List<Camp> findCampsByType(int type);
    List<Camp> findCampsByStatus(int status);
    List<Camp> findCampsByParams(Map<String, Object> params);
    List<Camp> findCampsByStatusWithTag(int status);
    List<Camp> findCampsByTypeWithTag(int type);
    List<CampWithTeacher> findAllCampsWithTeacher();
    List<CampList> findCampList(long userId);
    int addFavor(long id);
}
