package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.CampDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by tqyao.
 */
@Mapper
public interface CampDetailMapper {
    int add(CampDetail campDetail);
    int update(CampDetail campDetail);
    int deleteByIds(String[] ids);
}
