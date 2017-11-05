package com.platform.parent.response.camp;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 */
public class TopicWithGroupId {
    private long topicId;
    private String groupId;
    private String name;
    private Timestamp updated;
    private boolean temp;
    private boolean pri;
    private boolean essence, top;

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
}
