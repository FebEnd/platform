package com.platform.parent.response.camp;

import com.platform.parent.mybatis.bean.Tag;
import com.platform.parent.mybatis.bean.Teacher;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tqyao.
 */
public class CampWithTeacher {
    private long id;
    private int type, favor, maxLimit, minLimit, status;
    private BigDecimal price0, price1, price2;
    private String description, comment;

    private List<Teacher> teachers;
    private List<Tag> tags;

    public CampWithTeacher tags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }
    public CampWithTeacher description(String description) {
        this.description = description;
        return this;
    }

    public CampWithTeacher comment(String comment) {
        this.comment = comment;
        return this;
    }
    public CampWithTeacher teacher(List<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }
    public CampWithTeacher id(long id) {
        this.id = id;
        return this;
    }

    public CampWithTeacher type(int type) {
        this.type = type;
        return this;
    }

    public CampWithTeacher favor(int favor) {
        this.favor = favor;
        return this;
    }

    public CampWithTeacher maxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
        return this;
    }

    public CampWithTeacher minLimit(int minLimit) {
        this.minLimit = minLimit;
        return this;
    }

    public CampWithTeacher status(int status) {
        this.status = status;
        return this;
    }

    public CampWithTeacher price0(BigDecimal price0) {
        this.price0 = price0;
        return this;
    }

    public CampWithTeacher price1(BigDecimal price1) {
        this.price1 = price1;
        return this;
    }

    public CampWithTeacher price2(BigDecimal price2) {
        this.price2 = price2;
        return this;
    }

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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
