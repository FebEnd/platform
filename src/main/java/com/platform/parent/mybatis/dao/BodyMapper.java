package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.msgpayload.Body;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by tqyao.
 */
@Mapper
public interface BodyMapper {
    int add(Body body);
}
