package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 */
public class ChatGroup {
    private String id, name, description, owner, member;

    public ChatGroup id(String id) {
        this.id = id;
        return this;
    }
    public ChatGroup name(String name) {
        this.name = name;
        return this;
    }
    public ChatGroup description(String description) {
        this.description = description;
        return this;
    }
    public ChatGroup owner(String owner) {
        this.owner = owner;
        return this;
    }
    public ChatGroup member(String member) {
        this.member = member;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
