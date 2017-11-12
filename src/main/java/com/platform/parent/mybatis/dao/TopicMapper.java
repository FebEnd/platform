package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Component
@Mapper
public interface TopicMapper {
    int add(Topic topic);
    int update(Topic topic);
    int deleteByIds(String[] ids);
    Topic findTopicById(long topicId);
    Topic findTopicByGroupId(String groupId);
    List<Topic> findTopicAccessible(long userId);
    List<Topic> findTopicEssence(long campId);
    List<Topic> findTopicByCampId(long campId);
    int addRead(long topicId);
    int clearTopicsOverSevenDays();
    List<Topic> findTopicsOverSenvenDays();

}
