package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.bean.UserDetail;
import com.platform.parent.mybatis.dao.UserDetailMapper;
import com.platform.parent.mybatis.dao.UserMapper;
import com.platform.parent.mybatis.service.UserService;
import com.platform.parent.response.camp.UserMini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserDetailMapper detailMapper;

    @Override
    public long add(User user) {
        User user1;
        if ((user1 = findUserByPhone(user.getPhone())) != null) {
            //already existed
            return user1.getId();
        } else {
            int u = this.userMapper.add(user);
            UserDetail detail = new UserDetail().id(user.getId());
            int d = this.detailMapper.add(detail);
            return ((u > 0) && (d > 0)) ? 1 : 0;
        }
    }

    @Override
    public int update(User user, UserDetail detail) {
        System.out.println("update");
        this.detailMapper.update(detail);
        System.out.println("user");
        return this.userMapper.update(user);
    }

    @Override
    public int deleteByIds(String[] ids) {
        int u = this.userMapper.deleteByIds(ids);
        int d = this.detailMapper.deleteByIds(ids);
        return ((u > 0) && (d > 0)) ? 1 : 0;
    }

    @Override
    public User queryUserById(long id) {
        return this.userMapper.queryUserById(id);
    }

    @Override
    public User queryUserByIdWithDetail(long id) {
        return this.userMapper.queryUserByIdWithDetail(id);
    }

    @Override
    public List<User> findUserByCampId(long campId) {
        return this.userMapper.findUserByCampId(campId);
    }

    @Override
    public User findUserByPhone(String phone) {
        return this.userMapper.findUserByPhone(phone);
    }

    @Override
    public User findUserByPhoneWithRole(String phone) {
        return this.userMapper.findUserByPhoneWithRole(phone);
    }

    @Override
    public List<User> queryUserList(Map<String, Object> params) {
        return this.userMapper.queryUserList(params);
    }

    @Override
    public List<UserMini> findRoleByCampId(int role, long campId) {
        return this.userMapper.findRoleByCampId(role, campId);
    }
}
