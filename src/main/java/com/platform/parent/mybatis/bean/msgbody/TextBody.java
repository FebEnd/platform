package com.platform.parent.mybatis.bean.msgbody;

/**
 * Created by tqyao.
 */
public class TextBody extends Body{
    private String msg, type;

    public TextBody msg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
