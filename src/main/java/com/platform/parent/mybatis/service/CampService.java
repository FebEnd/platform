package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Camp;
import com.platform.parent.response.camp.CampWithTeacher;

import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
public interface CampService {
    int add(Camp camp);
    int update(Camp camp);
    int deleteByIds(String[] ids);
    Camp queryCampById(long id);
    List<Camp> findCampsByType(int type);
    List<Camp> findCampsByStatus(int status);
    List<Camp> findCampsByParams(Map<String, Object> params);
    List<Camp> findCampsByStatusWithTag(int status);
    List<Camp> findCampsByTypeWithTag(int type);
    List<CampWithTeacher> findAllCampsWithTeacher();
}
