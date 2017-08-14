package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 * id BIGINT(20) NOT NULL AUTO_INCREMENT,
 * camp_id BIGINT(20) NOT NULL ,
 * tag_id BIGINT(20) NOT NULL ,
 */
public class CampTag {
    private long id, campId, tagId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public CampTag(long campId, long tagId) {

        this.campId = campId;
        this.tagId = tagId;
    }

    public CampTag(long id, long campId, long tagId) {

        this.id = id;
        this.campId = campId;
        this.tagId = tagId;
    }
}
