package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.TopicInvolve;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by tqyao.
 */
@Mapper
public interface TopicInvolveMapper {

    int add(TopicInvolve involve);
    int update(TopicInvolve involve);
    int deleteByIds(String[] ids);
    TopicInvolve queryTopicInvolveById(long id);
    TopicInvolve queryTopicInvolveByUserIdAndTopicId(@Param("userId") long userId, @Param("topicId") long topicId);
    int queryInvolveCountByTopicId(long topicId);
}
