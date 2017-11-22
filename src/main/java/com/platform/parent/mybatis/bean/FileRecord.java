package com.platform.parent.mybatis.bean;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 */
public class FileRecord {
    private long id;
    private String filename, url;
    private long campId; // -1 public, others campId
    private long ownerId;
    private Timestamp created, updated;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
