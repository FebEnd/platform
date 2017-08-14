package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.User;

import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
public interface UserService {
    int add(User user);
    int update(User user);
    int deleteByIds(String[] ids);
    User queryUserById(long id);
    List<User> queryUserList(Map<String, Object> params);
}
