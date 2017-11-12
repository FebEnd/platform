package com.platform.parent.mybatis.bean.msgpayload;

/**
 * Created by tqyao.
 */
public class Body {
    private String type;//消息类型，见enum
    private String msg;//消息内容，txt类型
    private int fileLength;//文件大小，单位byte，img,audio,video,file类型
    private String filename;//文件名
    private String secret;//上传文件后会返回的
    private String url;//文件上传地址
    private Size size;//图片宽高
    private int length;//音视频秒数
    private String thumb;//视频缩略图url
    private String thumbSecret;//上传视频缩略图后返回
    private String addr;//地址
    private double lat,lng;//纬度，经度

    private long bodyId;
    private String msgId;


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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size.height + "," +  size.width;
    }

    public void setSize(String size) {
        String[] i = size.split(",");

        this.size = new Size(Integer.valueOf(i[0].trim()), Integer.valueOf(i[1].trim()));
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getThumbSecret() {
        return thumbSecret;
    }

    public void setThumbSecret(String thumbSecret) {
        this.thumbSecret = thumbSecret;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getBodyId() {
        return bodyId;
    }

    public void setBodyId(long bodyId) {
        this.bodyId = bodyId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    enum Type {
        TXT("txt"),
        IMG("img"),
        AUDIO("audio"),
        VIDEO("video"),
        FILE("file");
        private String type;

        private Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return this.type;
        }
    }

    static class Size {
        private int height, width;

        public Size(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

}




