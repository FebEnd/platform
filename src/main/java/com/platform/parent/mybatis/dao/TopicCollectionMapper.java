package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.TopicCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
public interface TopicCollectionMapper {
    int add(TopicCollection collection);
    int update(TopicCollection collection);
    int deleteByIds(String[] ids);
    TopicCollection findTopicCollectionById(long collectionId);
    List<TopicCollection> findTopicCollectionByUserId(long userId);
    List<TopicCollection> findTopicCollectionByTopicId(long topicId);
    List<TopicCollection> findTopicCollectionByLine();
}
