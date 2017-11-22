package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.User;
import com.platform.parent.response.camp.UserMini;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface UserMapper {
    int add(User user);
    int update(User user);
    int updateAOrN(User user);
    int deleteByIds(String[] ids);
    User queryUserById(long id);
    User queryUserByIdWithDetail(long id);
    List<User> findUserByCampId(long campId);
    User findUserByPhone(String phone);
    User findUserByPhoneWithRole(String phone);
    List<User> queryUserList(Map<String, Object> params);
    List<UserMini> findRoleByCampId(@Param("role") int role, @Param("campId") long campId);
}
