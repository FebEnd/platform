package com.platform.parent.security;

import com.platform.parent.mybatis.bean.Role;
import com.platform.parent.mybatis.bean.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tqyao.
 */
public class JwtUserFactory {
    private JwtUserFactory() {

    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getPhone(),
                user.getNickname(),
                user.getPassword(),
                user.getCity(),
                user.getLiveDistrict(),
                user.getTargetDistrict(),
                user.getChildBirth(),
                user.getChildGrade(),
                user.getChildGender(),
                user.getChildSchool(),
                mapToGrantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
        List<GrantedAuthority> result = new ArrayList<>();
        for (Role role : authorities) {
            result.add(new SimpleGrantedAuthority(role.getName()));
        }
        return result;
    }
}
