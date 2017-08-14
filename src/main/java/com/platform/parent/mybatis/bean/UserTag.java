package com.platform.parent.mybatis.bean;

import java.util.List;

/**
 * Created by tqyao.
 * id BIGINT(20) NOT NULL AUTO_INCREMENT,
 * user_id BIGINT(20) NOT NULL ,
 * tag_id BIGINT(20) NOT NULL ,
 */
public class UserTag {
    private long id, userId, tagId;
    private List<Tag> tags;

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

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public UserTag(long userId, long tagId) {

        this.userId = userId;
        this.tagId = tagId;
    }

    public UserTag(long id, long userId, long tagId) {

        this.id = id;
        this.userId = userId;
        this.tagId = tagId;
    }

    public UserTag(long id, long userId, long tagId, List<Tag> tags) {
        this.id = id;
        this.userId = userId;
        this.tagId = tagId;
        this.tags = tags;
    }

    public UserTag(long userId, long tagId, List<Tag> tags) {
        this.userId = userId;
        this.tagId = tagId;
        this.tags = tags;
    }
}
