package com.platform.parent.mybatis.bean;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 * id INT(11) NOT NULL AUTO_INCREMENT,
 * speaker_id INT(11) NOT NULL,
 * content TEXT NOT NULL,
 * time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 */
public class TopicContent {
    private long id;
    private long speakerId;
    private String content;
    private Timestamp time;

    //todo  change to easemob's msg struct

   /**public TopicContent(long id, long speakerId, String content, Timestamp time) {
        this.id = id;
        this.speakerId = speakerId;
        this.content = content;
        this.time = time;
    }

    public TopicContent(long speakerId, String content, Timestamp time) {
        this.speakerId = speakerId;
        this.content = content;
        this.time = time;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(long speakerId) {
        this.speakerId = speakerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
