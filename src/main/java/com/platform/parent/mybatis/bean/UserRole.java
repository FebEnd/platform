package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 * id BIGINT(20) NOT NULL AUTO_INCREMENT,
 * user_id BIGINT(20) NOT NULL ,
 * role_id BIGINT(20) NOT NULL ,
 */
public class UserRole {
    private long id, userId, roleId;

    public UserRole id(long id){
        this.id = id;
        return this;
    }
    public UserRole userId(long userId) {
        this.userId = userId;
        return this;
    }
    public UserRole roleId(long roleId) {
        this.roleId = roleId;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
