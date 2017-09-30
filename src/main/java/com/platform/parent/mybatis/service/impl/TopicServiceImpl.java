package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Topic;
import com.platform.parent.mybatis.dao.TopicMapper;
import com.platform.parent.mybatis.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicMapper topicMapper;

    @Override
    public int add(Topic topic) {
        return this.topicMapper.add(topic);
    }

    @Override
    public int update(Topic topic) {
        return this.topicMapper.update(topic);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.topicMapper.deleteByIds(ids);
    }

    @Override
    public Topic findTopicById(long topicId) {
        return this.topicMapper.findTopicById(topicId);
    }

    @Override
    public Topic findTopicByGroupId(String groupId) {
        return this.topicMapper.findTopicByGroupId(groupId);
    }

    @Override
    public List<Topic> findTopicAccessible(long userId) {
        return this.topicMapper.findTopicAccessible(userId);
    }
}
