package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Location;
import com.platform.parent.mybatis.bean.School;
import com.platform.parent.mybatis.dao.LocationMapper;
import com.platform.parent.mybatis.dao.SchoolMapper;
import com.platform.parent.mybatis.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolMapper schoolMapper;
    @Autowired
    LocationMapper locationMapper;

    @Override
    public int add(School school, Location location) {
        Location sl = this.locationMapper.findLocationById(school.getLocationId());
        if (sl != null) {
            //location already existed
            return this.schoolMapper.add(school);
        } else {
            //no such location
            this.locationMapper.add(location);
            school.setLocationId(location.getId());
            return this.schoolMapper.add(school);
        }
    }

    @Override
    public int updateLocation(Location location) {
        return this.locationMapper.update(location);
    }

    @Override
    public int updateSchool(School school) {
        return this.schoolMapper.update(school);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.schoolMapper.deleteByIds(ids);
    }

    @Override
    public School findSchoolById(long id) {
        return this.schoolMapper.findSchoolById(id);
    }

    @Override
    public School findSchoolByFullName(String name) {
        School school = this.schoolMapper.findSchoolByFullName(name);
        this.schoolMapper.addHeat(school.getId());
        return school;
    }

    @Override
    public School findSchoolByAlias(String alias) {
        School school = this.schoolMapper.findSchoolByAlias(alias);
        this.schoolMapper.addHeat(school.getId());
        return school;
    }

    public List<School> findSchoolFuzzy(String fuzzy) {
        List<School> schools = this.schoolMapper.findSchoolFuzzy(fuzzy);
        for (School school : schools) {
            this.schoolMapper.addHeat(school.getId());
        }
        return schools;
    }

    @Override
    public List<School> findAllSchools() {
        return this.schoolMapper.findAllSchool();
    }

    @Override
    public List<School> findHotSchool(String location) {
        return this.schoolMapper.findHotSchool(location);
    }
}
