package com.platform.parent.mybatis.bean;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 * id BIGINT(20) NOT NULL AUTO_INCREMENT,
 * owner_id BIGINT(20) NOT NULL,
 * name VARCHAR(100) NOT NULL,
 * pri BOOLEAN DEFAULT FALSE ,
 * essence BOOLEAN DEFAULT FALSE,
 * top BOOLEAN DEFAULT FALSE ,
 * group_id VARCHAR(20) NOT NULL ,
 * camp_id BIGINT(20) DEFAULT NULL,
 */
public class Topic {
    private long id;
    private long ownerId;
    private String name;
    private boolean pri, essence, top, temp;
    private long campId;
    private String groupId;
    private Timestamp created, updated;

    private int read, reply;
    private String owner,avatar;

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public Topic id(long id) {
        this.id = id;
        return this;
    }

    public Topic temp(boolean temp) {
        this.temp = temp;
        return this;
    }

    public Topic created(Timestamp created) {
        this.created = created;
        return this;
    }

    public Topic updated(Timestamp updated) {
        this.updated = updated;
        return this;
    }

    public Topic ownerId(long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Topic name(String name) {
        this.name = name;
        return this;
    }

    public Topic pri(boolean pri) {
        this.pri = pri;
        return this;
    }

    public Topic essence(boolean essence) {
        this.essence = essence;
        return this;
    }

    public Topic top(boolean top) {
        this.top = top;
        return this;
    }

    public Topic groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public Topic campId(long campId) {
        this.campId = campId;
        return this;
    }

    /**
     * public Topic(long id, long ownerId, String name, int type, boolean sticky, long campId) {
     * this.id = id;
     * this.ownerId = ownerId;
     * this.name = name;
     * this.type = type;
     * this.sticky = sticky;
     * this.campId = campId;
     * }
     * <p>
     * public Topic(long ownerId, String name, int type, boolean sticky, long campId) {
     * this.ownerId = ownerId;
     * this.name = name;
     * this.type = type;
     * this.sticky = sticky;
     * this.campId = campId;
     * }
     * <p>
     * public Topic(long ownerId, String name, int type, long campId) {
     * this.ownerId = ownerId;
     * this.name = name;
     * this.type = type;
     * this.campId = campId;
     * this.sticky = false;
     * }
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public boolean isPri() {
        return pri;
    }

    public void setPri(boolean pri) {
        this.pri = pri;
    }

    public boolean isEssence() {
        return essence;
    }

    public void setEssence(boolean essence) {
        this.essence = essence;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public boolean isTemp() {
        return temp;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
