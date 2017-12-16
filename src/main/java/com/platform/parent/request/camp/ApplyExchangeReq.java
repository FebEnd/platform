package com.platform.parent.request.camp;

import javax.validation.constraints.NotNull;

/**
 * Created by tqyao.
 */
public class ApplyExchangeReq {
    @NotNull
    private long from;
    @NotNull
    private String reason;
    @NotNull
    private long userId;

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
