package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Location;
import com.platform.parent.mybatis.bean.School;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface SchoolService {
    int add(School school, Location location);
    int updateSchool(School school);
    int updateLocation(Location location);
    int deleteByIds(String[] ids);
    School findSchoolById(long id);
    School findSchoolByFullName(String name);
    School findSchoolByAlias(String alias);
    List<School> findSchoolFuzzy(String fuzzy, String city);
    List<School> findAllSchools();
    List<School> findHotSchool(String location);
}
