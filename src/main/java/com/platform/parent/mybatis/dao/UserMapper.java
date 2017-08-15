package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.User;
import org.apache.ibatis.annotations.Mapper;
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
    int deleteByIds(String[] ids);
    User queryUserById(long id);
    User findUserByPhone(String phone);
    User findUserByPhoneWithRole(String phone);
    List<User> queryUserList(Map<String, Object> params);
}
