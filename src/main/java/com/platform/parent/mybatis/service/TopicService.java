package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Topic;
import com.platform.parent.mybatis.bean.User;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface TopicService {
    int add(Topic topic);
    int add(Topic topic, List<User> teachers);
    int update(Topic topic);
    int deleteByIds(String[] ids);
    Topic findTopicById(long topicId);
    Topic findTopicByGroupId(String groupId);
    List<Topic> findTopicAccessible(long userId);
    List<Topic> findTopicEssence(long campId);
    List<Topic> findTopicByCampId(long campId);
    int addRead(long topicId);
    int clearTopicsOverSevenDays();
    List<Topic> findTopicsOverSevenDays();
}
