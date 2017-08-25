package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface SchoolMapper {
    int add(School school);
    int update(School school);
    int deleteByIds(String[] ids);
    School findSchoolById(long id);
    School findSchoolByFullName(String name);
    School findSchoolByAlias(String alias);
    List<School> findSchoolFuzzy(String fuzzy);
    List<School> findAllSchool();
    int addHeat(long id);
}
