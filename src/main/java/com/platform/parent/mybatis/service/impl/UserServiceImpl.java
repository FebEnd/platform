package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.dao.UserMapper;
import com.platform.parent.mybatis.service.UserService;
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
    @Override
    public int add(User user) {
        return this.userMapper.add(user);
    }

    @Override
    public int update(User user) {
        return this.userMapper.update(user);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.userMapper.deleteByIds(ids);
    }

    @Override
    public User queryUserById(long id) {
        return this.userMapper.queryUserById(id);
    }

    @Override
    public List<User> queryUserList(Map<String, Object> params) {
        return this.userMapper.queryUserList(params);
    }
}
