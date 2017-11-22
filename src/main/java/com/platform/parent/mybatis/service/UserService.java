package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.bean.UserDetail;
import com.platform.parent.response.camp.UserMini;

import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
public interface UserService {
    long add(User user);
    int update(User user, UserDetail detail);
    int updateAOrN(User user);
    int deleteByIds(String[] ids);
    User queryUserById(long id);
    User queryUserByIdWithDetail(long id);
    List<User> findUserByCampId(long campId);
    User findUserByPhone(String phone);
    User findUserByPhoneWithRole(String phone);
    List<User> queryUserList(Map<String, Object> params);
    List<UserMini> findRoleByCampId(int role, long campId);
}
