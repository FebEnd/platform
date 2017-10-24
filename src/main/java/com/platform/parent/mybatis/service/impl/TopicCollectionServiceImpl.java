package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.TopicCollection;
import com.platform.parent.mybatis.dao.TopicCollectionMapper;
import com.platform.parent.mybatis.service.TopicCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class TopicCollectionServiceImpl implements TopicCollectionService {
    @Autowired
    TopicCollectionMapper mapper;
    @Override
    public int add(TopicCollection collection) {
        return this.mapper.add(collection);
    }

    @Override
    public int update(TopicCollection collection) {
        return this.mapper.update(collection);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.mapper.deleteByIds(ids);
    }

    @Override
    public TopicCollection findTopicCollectionById(long collectionId) {
        return this.mapper.findTopicCollectionById(collectionId);
    }

    @Override
    public List<TopicCollection> findTopicCollectionByUserId(long userId) {
        return this.mapper.findTopicCollectionByUserId(userId);
    }

    @Override
    public List<TopicCollection> findTopicCollectionByTopicId(long topicId) {
        return this.mapper.findTopicCollectionByTopicId(topicId);
    }

    @Override
    public List<TopicCollection> findTopicCollectionByLine() {
        return this.mapper.findTopicCollectionByLine();
    }
}
