package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface UserRoleMapper {
    int add(UserRole userRole);
    int update(UserRole userRole);
    int deleteByIds(String[] ids);
}
