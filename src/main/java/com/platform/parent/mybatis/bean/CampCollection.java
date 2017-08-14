package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 * id INT(11) NOT NULL AUTO_INCREMENT,
 * user_id INT(11) NOT NULL,
 * camp_id INT(11) NOT NULL,
 */
public class CampCollection {
    private long id, userId, campId;

    public CampCollection(long id, long userId, long campId) {
        this.id = id;
        this.userId = userId;
        this.campId = campId;
    }

    public CampCollection(long userId, long campId) {
        this.userId = userId;
        this.campId = campId;
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

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }
}
