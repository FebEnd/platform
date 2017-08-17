package com.platform.parent.mybatis.bean.msgbody;

/**
 * Created by tqyao.
 */
public class Body {
    private String type;

    public Body type(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
