package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.CampAttend;
import com.platform.parent.mybatis.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface CampAttendMapper {
    int add(CampAttend attend);
    int update(CampAttend attend);
    int deleteByIds(String[] ids);
    CampAttend findCampAttendById(long id);
    List<CampAttend> findCampAttendByUserIdAndCampId(@Param("userId") long userId, @Param("campId") long campId);
    List<CampAttend> findCampAttendByUserId(long userId);
    List<CampAttend> findCampAttendByCampId(long campId);
    long countCampAttendByUserId(long userId);
    long countCampAttendByCampId(long campId);
    List<User> findTeachersAndObserverByCampId(long campId);
}
