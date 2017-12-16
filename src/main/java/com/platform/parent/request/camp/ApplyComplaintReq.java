package com.platform.parent.request.camp;

import javax.validation.constraints.NotNull;

/**
 * Created by tqyao.
 */
public class ApplyComplaintReq {
    @NotNull
    private String title, content;
    @NotNull
    private long campId, userId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
