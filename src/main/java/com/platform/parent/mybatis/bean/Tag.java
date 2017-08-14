package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 * id BIGINT(20) NOT NULL AUTO_INCREMENT,
 * content TEXT NOT NULL ,
 */
public class Tag {
    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Tag(String content) {

        this.content = content;
    }

    public Tag(long id, String content) {

        this.id = id;
        this.content = content;
    }
}
