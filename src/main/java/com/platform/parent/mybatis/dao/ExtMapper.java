package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.msgpayload.Ext;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by tqyao.
 */
@Mapper
public interface ExtMapper {
    int add(Ext ext);
}
