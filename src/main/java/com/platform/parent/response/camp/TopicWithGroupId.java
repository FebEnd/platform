package com.platform.parent.response.camp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 */
public class TopicWithGroupId {
    static final int TEMP = 0, PRI = 1, ESSENCE = 2, TOP = 3, ESSENCE_TOP = 4;
    private long topicId;
    private String groupId;
    private String name;
    private Timestamp updated;
    private int level; // 0 临时私聊，1 永久私聊，2 精华话题，3 置顶话题， 4 精华+置顶

    @JsonIgnore
    private boolean temp;
    @JsonIgnore
    private boolean pri;
    @JsonIgnore
    private boolean essence;
    @JsonIgnore
    private boolean top;


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

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isTemp() {
        return temp;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public boolean isPri() {
        return pri;
    }

    public void setPri(boolean pri) {
        this.pri = pri;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        if (temp) {
            this.level = TEMP;
        } else if (pri) {
            this.level = PRI;
        } else if (essence && !top) {
            this.level = ESSENCE;
        } else if (!essence && top) {
            this.level = TOP;
        } else if (essence && top) {
            this.level = ESSENCE_TOP;
        } else {
            this.level = TEMP;
        }
    }
}
