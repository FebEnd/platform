package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 * id INT(11) NOT NULL AUTO_INCREMENT,
 * originator_id INT(11) NOT NULL,/* 发起者 id
 * title VARCHAR(100) NOT NULL,
 * type INT(11) NOT NULL, /* 0 公开群聊话题, 1 临时1v1, 2 持久1v1
 * sticky BOOLEAN DEFAULT FALSE, /* 是否精华
 * camp_id INT(11) DEFAULT NULL, /* 话题归属， NULL 表示不属于群聊
 */
public class Topic {
    private long id;
    private long originatorId;
    private String title;
    private int type;
    private boolean sticky;
    private long campId;

    public Topic(long id, long originatorId, String title, int type, boolean sticky, long campId) {
        this.id = id;
        this.originatorId = originatorId;
        this.title = title;
        this.type = type;
        this.sticky = sticky;
        this.campId = campId;
    }

    public Topic(long originatorId, String title, int type, boolean sticky, long campId) {
        this.originatorId = originatorId;
        this.title = title;
        this.type = type;
        this.sticky = sticky;
        this.campId = campId;
    }

    public Topic(long originatorId, String title, int type, long campId) {
        this.originatorId = originatorId;
        this.title = title;
        this.type = type;
        this.campId = campId;
        this.sticky = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(long originatorId) {
        this.originatorId = originatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSticky() {
        return sticky;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }
}
