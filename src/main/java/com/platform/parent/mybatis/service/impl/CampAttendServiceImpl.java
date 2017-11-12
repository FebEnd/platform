package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.CampAttend;
import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.dao.CampAttendMapper;
import com.platform.parent.mybatis.service.CampAttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class CampAttendServiceImpl implements CampAttendService {
    @Autowired
    CampAttendMapper mapper;
    @Override
    public int add(CampAttend attend) {
        return this.mapper.add(attend);
    }

    @Override
    public int update(CampAttend attend) {
        return this.mapper.update(attend);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.mapper.deleteByIds(ids);
    }

    @Override
    public CampAttend findCampAttendById(long id) {
        return this.mapper.findCampAttendById(id);
    }

    @Override
    public List<CampAttend> findCampAttendByUserIdAndCampId(long userId, long campId) {
        return this.mapper.findCampAttendByUserIdAndCampId(userId, campId);
    }

    @Override
    public List<CampAttend> findCampAttendByUserId(long userId) {
        return this.mapper.findCampAttendByUserId(userId);
    }

    @Override
    public List<CampAttend> findCampAttendByCampId(long campId) {
        return this.mapper.findCampAttendByCampId(campId);
    }

    @Override
    public long countCampAttendByUserId(long userId) {
        return this.mapper.countCampAttendByUserId(userId);
    }

    @Override
    public long countCampAttendByCampId(long campId) {
        return this.mapper.countCampAttendByCampId(campId);
    }

    @Override
    public List<User> findTeachersAndObserverByCampId(long campId) {
        return this.mapper.findTeachersAndObserverByCampId(campId);
    }
}
