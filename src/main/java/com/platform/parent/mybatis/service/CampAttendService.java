package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.CampAttend;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface CampAttendService {
    int add(CampAttend attend);
    int update(CampAttend attend);
    int deleteByIds(String[] ids);
    CampAttend findCampAttendById(long id);
    CampAttend findCampAttendByUserIdAndCampId(long userId, long campId);
    List<CampAttend> findCampAttendByUserId(long userId);
    List<CampAttend> findCampAttendByCampId(long campId);
    long countCampAttendByUserId(long userId);
    long countCampAttendByCampId(long campId);
}
