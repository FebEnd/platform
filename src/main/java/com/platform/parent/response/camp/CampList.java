package com.platform.parent.response.camp;

import com.platform.parent.mybatis.bean.Teacher;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tqyao.
 */
public class CampList {
    private long id;
    private BigDecimal price;
    private String comment;
    private String title, subtitle;

    private List<Teacher> teachers;

    public CampList id(long id) {
        this.id = id;
        return this;
    }

    public CampList price(BigDecimal price) {
        this.price = price;
        return this;
    }
    public CampList comment(String comment) {
        this.comment = comment;
        return this;
    }
    public CampList title(String title) {
        this.title = title;
        return this;
    }
    public CampList subtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }
    public CampList teachers(List<Teacher> teachers){
        this.teachers = teachers;
        return this;
    }

    public CampList() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
