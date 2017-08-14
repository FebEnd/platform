package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 * id INT(11) NOT NULL AUTO_INCREMENT,
 * user_id INT(11) NOT NULL,
 * topic_id INT(11) NOT NULL,
 */
public class TopicInvolve {
    private long id, userId, topicId;

    public TopicInvolve(long id, long userId, long topicId) {
        this.id = id;
        this.userId = userId;
        this.topicId = topicId;
    }

    public TopicInvolve(long userId, long topicId) {
        this.userId = userId;
        this.topicId = topicId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
}
