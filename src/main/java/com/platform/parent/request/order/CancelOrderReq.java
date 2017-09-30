package com.platform.parent.request.order;

/**
 * Created by tqyao.
 */
public class CancelOrderReq {
    private long userId, orderId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
