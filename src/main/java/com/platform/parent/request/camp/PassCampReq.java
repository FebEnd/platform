package com.platform.parent.request.camp;

import java.math.BigDecimal;

/**
 * Created by tqyao.
 * id BIGINT(20) NOT NULL AUTO_INCREMENT,
 * type INT(11) DEFAULT 0, #0 家长创建， 1 专项， 2 机构
 * favor INT(11) DEFAULT 0,#点赞数
 * description TEXT,#导师说
 * comment TEXT,#平台点评
 * max_limit INT(11) DEFAULT 0,#最大人数限制
 * min_limit INT(11) DEFAULT 0,#最小人数限制
 * status INT(11) DEFAULT 0, #0 初始, 1 上线, 2 开课, 3 下线
 * price0 DECIMAL(10,2),
 * price1 DECIMAL(10,2),
 * price2 DECIMAL(10,2),
 * title TEXT,
 * subtitle TEXT,
 */
public class PassCampReq {
    private long userId;
    private int type;
    private String description, comment;
    private int maxLimit, minLimit;
    private BigDecimal price0,price1, price2;
    private String title, subtitle;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    public int getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(int minLimit) {
        this.minLimit = minLimit;
    }

    public BigDecimal getPrice0() {
        return price0;
    }

    public void setPrice0(BigDecimal price0) {
        this.price0 = price0;
    }

    public BigDecimal getPrice1() {
        return price1;
    }

    public void setPrice1(BigDecimal price1) {
        this.price1 = price1;
    }

    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal price2) {
        this.price2 = price2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
