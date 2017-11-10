package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.TopicInvolve;

/**
 * Created by tqyao.
 */
public interface TopicInvolveService {
    int add(TopicInvolve involve);
    int update(TopicInvolve involve);
    int deleteByIds(String[] ids);
    TopicInvolve queryTopicInvolveById(long id);
    TopicInvolve queryTopicInvolveByUserIdAndTopicId(long userId, long topicId);
    int queryInvolveCountByTopicId(long topicId);
}
