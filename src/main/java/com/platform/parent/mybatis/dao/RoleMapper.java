package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface RoleMapper {
    int add(Role role);
    int update(Role role);
    int deleteByIds(String[] ids);
    Role findRoleById(long id);
    List<Role> findAllRole();
}
