package com.platform.parent.mybatis.bean;

import com.platform.parent.mybatis.bean.msgpayload.Payload;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 */
public class Message {
    private String msgId;
    private Timestamp timestamp;
    private String direction;
    private String to;
    private String from;
    private String chatType;

    private Payload payload;

    public Message msgId(String msgId) {
        this.msgId = msgId;
        return this;
    }
    public Message timestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    public Message direction(String direction) {
        this.direction = direction;
        return this;
    }
    public Message to(String to) {
        this.to = to;
        return this;
    }
    public Message from(String from) {
        this.from = from;
        return this;
    }
    public Message chatType(String chatType) {
        this.chatType = chatType;
        return this;
    }
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
