package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Topic;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface TopicService {
    int add(Topic topic);
    int update(Topic topic);
    int deleteByIds(String[] ids);
    Topic findTopicById(long topicId);
    Topic findTopicByGroupId(String groupId);
    List<Topic> findTopicAccessible(long userId);
}
