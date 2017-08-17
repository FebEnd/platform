package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.School;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
public interface SchoolMapper {
    int add(School school);
    int update(School school);
    int deleteByIds(String[] ids);
    School findSchoolById(long id);
    School findSchoolByFullName(String name);
    School findSchoolByAlias(String alias);
    List<School> findSchoolFuzzy(String fuzzy);
}
