package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by tqyao.
 */
@Mapper
public interface MessageMapper {
    int add(Message message);
}
