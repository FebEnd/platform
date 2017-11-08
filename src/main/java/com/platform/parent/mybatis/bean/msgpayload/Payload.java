package com.platform.parent.mybatis.bean.msgpayload;

import java.util.List;

/**
 * Created by tqyao.
 */
public class Payload {
    private List<Body> bodies;
    private Ext ext;

    public Payload bodies(List<Body> bodies) {
        this.bodies = bodies;
        return this;
    }

    public Payload ext(Ext ext) {
        this.ext = ext;
        return this;
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public void setBodies(List<Body> bodies) {
        this.bodies = bodies;
    }

    public Ext getExt() {
        return ext;
    }

    public void setExt(Ext ext) {
        this.ext = ext;
    }
}
