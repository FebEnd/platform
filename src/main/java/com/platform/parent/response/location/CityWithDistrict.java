package com.platform.parent.response.location;

/**
 * Created by tqyao.
 */
public class CityWithDistrict {
    private long id;
    private String city, district;
    private boolean six;

    public CityWithDistrict id(long id) {
        this.id = id;
        return this;
    }

    public CityWithDistrict six(boolean six) {
        this.six = six;
        return this;
    }

    public CityWithDistrict city(String city) {
        this.city = city;
        return this;
    }

    public CityWithDistrict district(String district) {
        this.district = district;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
