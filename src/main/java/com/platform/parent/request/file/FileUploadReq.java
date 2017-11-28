package com.platform.parent.request.file;

import javax.validation.constraints.NotNull;

/**
 * Created by tqyao.
 */
public class FileUploadReq {
    @NotNull
    private long ownerId, campId;
    @NotNull
    private String filename;
    @NotNull
    private long size;
    @NotNull
    private String url;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
