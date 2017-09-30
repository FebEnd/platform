package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.ChatGroup;

/**
 * Created by tqyao.
 */
public interface ChatGroupService {
    int add(ChatGroup chatGroup);
    int update(ChatGroup chatGroup);
    int deleteByIds(String[] ids);
    ChatGroup findChatGroupById(String id);
}
