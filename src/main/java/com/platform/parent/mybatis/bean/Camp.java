package com.platform.parent.mybatis.bean;

import java.math.BigDecimal;

/**
 * Created by tqyao.
 * id INT(11) NOT NULL AUTO_INCREMENT,
 * type INT(11) DEFAULT 0, /*0 家长创建， 1 专项， 2 机构
 * favor INT(11) DEFAULT 0,/*点赞数
 * max_limit INT(11) DEFAULT 0,/*最大人数限制
 * min_limit INT(11) DEFAULT 0,/*最小人数限制
 * status INT(11) DEFAULT 0, /*0 初始, 1 上线, 2 开课, 3 下线
 * price0 DECIMAL(10,2),
 * price1 DECIMAL(10,2),
 * price2 DECIMAL(10,2),
 */
public class Camp {
    private long id;
    private int type, favor, maxLimit, minLimit, status;
    private BigDecimal price0, price1, price2;

    public Camp id(long id) {
        this.id = id;
        return this;
    }

    public Camp type(int type) {
        this.type = type;
        return this;
    }

    public Camp favor(int favor) {
        this.favor = favor;
        return this;
    }

    public Camp maxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
        return this;
    }

    public Camp minLimit(int minLimit) {
        this.minLimit = minLimit;
        return this;
    }

    public Camp status(int status) {
        this.status = status;
        return this;
    }

    public Camp price0(BigDecimal price0) {
        this.price0 = price0;
        return this;
    }

    public Camp price1(BigDecimal price1) {
        this.price1 = price1;
        return this;
    }

    public Camp price2(BigDecimal price2) {
        this.price2 = price2;
        return this;
    }

    /*public Camp(long id, int type, int favor, int maxLimit, int minLimit, int status, BigDecimal price0, BigDecimal price1, BigDecimal price2) {
        this.id = id;
        this.type = type;
        this.favor = favor;
        this.maxLimit = maxLimit;
        this.minLimit = minLimit;
        this.status = status;
        this.price0 = price0;
        this.price1 = price1;
        this.price2 = price2;
    }

    public Camp(int type, int favor, int maxLimit, int minLimit, int status, BigDecimal price0, BigDecimal price1, BigDecimal price2) {
        this.type = type;
        this.favor = favor;
        this.maxLimit = maxLimit;
        this.minLimit = minLimit;
        this.status = status;
        this.price0 = price0;
        this.price1 = price1;
        this.price2 = price2;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
