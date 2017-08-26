package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface UserDetailMapper {
    int add(UserDetail userDetail);
    int update(UserDetail userDetail);
    int deleteByIds(String[] ids);
}
