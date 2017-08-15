package com.platform.parent.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;

/**
 * Created by tqyao.
 */
public class JwtUser implements UserDetails {
    private long id;
    private String phone, nickname, password, city, liveDistrict, targetDistrict;
    private Date childBirth;
    private String childGrade;
    private String childGender, childSchool;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(long id, String phone, String nickname, String password, String city, String liveDistrict, String targetDistrict, Date childBirth, String childGrade, String childGender, String childSchool, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.phone = phone;
        this.nickname = nickname;
        this.password = password;
        this.city = city;
        this.liveDistrict = liveDistrict;
        this.targetDistrict = targetDistrict;
        this.childBirth = childBirth;
        this.childGrade = childGrade;
        this.childGender = childGender;
        this.childSchool = childSchool;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    @JsonIgnore
    public long getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.phone;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
