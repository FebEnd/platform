package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.TopicInvolve;
import com.platform.parent.mybatis.dao.TopicInvolveMapper;
import com.platform.parent.mybatis.service.TopicInvolveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tqyao.
 */
@Service
public class TopicInvolveServiceImpl implements TopicInvolveService {
    @Autowired
    TopicInvolveMapper mapper;
    @Override
    public int add(TopicInvolve involve) {
        return this.mapper.add(involve);
    }

    @Override
    public int update(TopicInvolve involve) {
        return this.mapper.update(involve);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.mapper.deleteByIds(ids);
    }

    @Override
    public TopicInvolve queryTopicInvolveById(long id) {
        return this.mapper.queryTopicInvolveById(id);
    }

    @Override
    public TopicInvolve queryTopicInvolveByUserIdAndTopicId(long userId, long topicId) {
        return this.mapper.queryTopicInvolveByUserIdAndTopicId(userId, topicId);
    }

    @Override
    public int queryInvolveCountByTopicId(long topicId) {
        return this.mapper.queryInvolveCountByTopicId(topicId);
    }
}
