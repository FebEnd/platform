package com.platform.parent.response.camp;

import com.platform.parent.mybatis.bean.Tag;

import java.util.List;

/**
 * Created by tqyao.
 */
public class CampTeacher {
    private long id;
    private int star;
    private String description;
    private int status;//状态 0 未申请，1 已申请未审核，2 已审核通过， 3 未通过

    private List<Tag> tags;
    private String nickname, avatar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
