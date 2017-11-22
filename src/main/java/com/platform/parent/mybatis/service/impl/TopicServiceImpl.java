package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Topic;
import com.platform.parent.mybatis.bean.TopicCollection;
import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.dao.TopicCollectionMapper;
import com.platform.parent.mybatis.dao.TopicMapper;
import com.platform.parent.mybatis.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicMapper topicMapper;
    @Autowired
    TopicCollectionMapper topicCollectionMapper;

    @Override
    public int add(Topic topic) {
        return this.topicMapper.add(topic);
    }

    @Override
    @Transactional
    public int add(Topic topic, List<User> teachers) {
        int i = this.topicMapper.add(topic);
        boolean j = true;
        for (User user : teachers) {
            TopicCollection collection = new TopicCollection().topicId(topic.getId()).userId(user.getId());
            int k = this.topicCollectionMapper.add(collection);
            if (k <= 0) {
                j = false;
            }
        }
        return (i> 0 && j) ? 1 : 0;
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

    @Override
    public List<Topic> findTopicEssence(long campId) {
        return this.topicMapper.findTopicEssence(campId);
    }

    @Override
    public List<Topic> findTopicByCampId(long campId) {
        return this.topicMapper.findTopicByCampId(campId);
    }

    @Override
    public int addRead(long topicId) {
        return this.topicMapper.addRead(topicId);
    }

    @Override
    public int clearTopicsOverSevenDays() {
        return this.topicMapper.clearTopicsOverSevenDays();
    }

    @Override
    public List<Topic> findTopicsOverSevenDays() {
        return this.topicMapper.findTopicsOverSevenDays();
    }
}
