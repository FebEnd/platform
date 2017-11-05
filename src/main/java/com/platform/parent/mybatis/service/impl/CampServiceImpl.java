package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Camp;
import com.platform.parent.mybatis.dao.CampMapper;
import com.platform.parent.mybatis.dao.UserMapper;
import com.platform.parent.mybatis.service.CampService;
import com.platform.parent.response.camp.*;
import com.platform.parent.response.school.CampWithTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
@Service
public class CampServiceImpl implements CampService {
    @Autowired
    CampMapper campMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public int add(Camp camp) {
        return this.campMapper.add(camp);
    }

    @Override
    public int update(Camp camp) {
        return this.campMapper.update(camp);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.campMapper.deleteByIds(ids);
    }

    @Override
    public Camp queryCampById(long id) {
        return this.campMapper.queryCampById(id);
    }

    @Override
    public Camp findCampByTeacherId(long id) {
        return this.campMapper.findCampByTeacherId(id);
    }

    @Override
    public CampWithTeacher findCampByIdWithDetail(long id) {
        return this.campMapper.findCampByIdWithDetail(id);
    }

    @Override
    public List<CampWithGroupId> findCampsActiveByUserId(long userId) {
        return this.campMapper.findCampsActiveByUserId(userId);
    }

    @Override
    public List<CampList> findCampListBySchoolId(long schoolId) {
        return this.campMapper.findCampListBySchoolId(schoolId);
    }

    @Override
    public List<Camp> findCampsByType(int type) {
        return this.campMapper.findCampsByType(type);
    }

    @Override
    public List<Camp> findCampsByStatus(int status) {
        return this.campMapper.findCampsByStatus(status);
    }

    @Override
    public List<Camp> findCampsByParams(Map<String, Object> params) {
        return this.campMapper.findCampsByParams(params);
    }

    @Override
    public List<Camp> findCampsByStatusWithTag(int status) {
        return this.campMapper.findCampsByStatusWithTag(status);
    }

    @Override
    public List<Camp> findCampsByTypeWithTag(int type) {
        return this.campMapper.findCampsByTypeWithTag(type);
    }

    @Override
    public List<CampWithTeacher> findAllCampsWithTeacher() {
        return this.campMapper.findAllCampsWithTeacher();
    }

    @Override
    public List<CampList> findCampList(long userId) {
        return this.campMapper.findCampList(userId);
    }

    @Override
    public List<CampWithTitle> findCampByTypeWithTitle(int type, String city) {
        return this.campMapper.findCampByTypeWithTitle(type, city);
    }

    @Override
    public int addFavor(long id) {
        return this.campMapper.addFavor(id);
    }

    @Override
    public CampDetail findCampDetailByCampId(long campId) {
        CampDetail campDetail = this.campMapper.findCampDetailByCampId(campId);
        List<UserMini> teachers = this.userMapper.findRoleByCampId(2,campId);
        List<UserMini> observers = this.userMapper.findRoleByCampId(3,campId);
        List<UserMini> members = this.userMapper.findRoleByCampId(0,campId);
        List<UserMini> managers = this.userMapper.findRoleByCampId(1,campId);
        List<UserMini> noneMembers = this.userMapper.findRoleByCampId(4,campId);
        campDetail.setTeachers(teachers);
        campDetail.setObservers(observers);
        campDetail.setMembers(members);
        campDetail.setManagers(managers);
        campDetail.setNoneMembers(noneMembers);
        return campDetail;
    }

}
