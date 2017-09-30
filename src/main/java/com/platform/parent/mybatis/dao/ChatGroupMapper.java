package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.ChatGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface ChatGroupMapper {
    int add(ChatGroup chatGroup);
    int update(ChatGroup chatGroup);
    int deleteByIds(String[] ids);
    ChatGroup findChatGroupById(String id);
}
