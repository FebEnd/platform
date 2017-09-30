package com.platform.parent.mybatis.bean;

/**
 * Created by tqyao.
 * id BIGINT(20) NOT NULL AUTO_INCREMENT,
 * province TEXT,
 * city TEXT NOT NULL ,
 * district TEXT NOT NULL ,
 */
public class Location {
    private long id;
    private String province, city, district;
    private boolean six;

    public Location id(long id){
        this.id =id;
        return this;
    }

    public Location six(boolean six) {
        this.six = six;
        return this;
    }
    public Location province(String province) {
        this.province = province;
        return this;
    }
    public Location city(String city) {
        this.city = city;
        return this;
    }
    public Location district(String district) {
        this.district = district;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public boolean isSix() {
        return six;
    }

    public void setSix(boolean six) {
        this.six = six;
    }
}
