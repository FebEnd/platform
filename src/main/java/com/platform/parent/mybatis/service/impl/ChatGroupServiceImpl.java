package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.ChatGroup;
import com.platform.parent.mybatis.dao.ChatGroupMapper;
import com.platform.parent.mybatis.service.ChatGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tqyao.
 */
@Service
public class ChatGroupServiceImpl implements ChatGroupService {
    @Autowired
    ChatGroupMapper chatGroupMapper;

    @Override
    public int add(ChatGroup chatGroup) {
        return this.chatGroupMapper.add(chatGroup);
    }

    @Override
    public int update(ChatGroup chatGroup) {
        return this.chatGroupMapper.update(chatGroup);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.chatGroupMapper.deleteByIds(ids);
    }

    @Override
    public ChatGroup findChatGroupById(String id) {
        return this.chatGroupMapper.findChatGroupById(id);
    }
}
