package com.platform.parent.request.camp;

/**
 * Created by tqyao.
 */
public class AddFavorReq {
    private long userId;
    private long campId;

    public AddFavorReq userId(long userId) {
        this.userId = userId;
        return this;
    }
    public AddFavorReq campId(long campId) {
        this.campId = campId;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }
}
