package com.platform.parent.security;

import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by tqyao.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userMapper.findUserByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with phone '%s'.", phone));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
