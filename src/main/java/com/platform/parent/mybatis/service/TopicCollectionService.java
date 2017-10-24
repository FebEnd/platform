package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.TopicCollection;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface TopicCollectionService {
    int add(TopicCollection collection);
    int update(TopicCollection collection);
    int deleteByIds(String[] ids);
    TopicCollection findTopicCollectionById(long collectionId);
    List<TopicCollection> findTopicCollectionByUserId(long userId);
    List<TopicCollection> findTopicCollectionByTopicId(long topicId);
    List<TopicCollection> findTopicCollectionByLine();
}
