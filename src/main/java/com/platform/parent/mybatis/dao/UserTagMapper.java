package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.UserTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface UserTagMapper {
    int add(UserTag userTag);
    int update(UserTag userTag);
    int deleteByIds(String[] ids);
}
