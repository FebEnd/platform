package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.SchoolAlias;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface SchoolAliasMapper {
    int add(SchoolAlias schoolAlias);
    int update(SchoolAlias schoolAlias);
    int deleteByIds(String[] ids);
}
