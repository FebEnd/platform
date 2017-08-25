package com.platform.parent.mybatis.bean;

import java.util.List;

/**
 * Created by tqyao.
 * id BIGINT(20) NOT NULL AUTO_INCREMENT,
 * full_name TEXT NOT NULL ,
 * alias TEXT,
 * location_id BIGINT(20) NOT NULL ,
 */
public class School {
    private long id, locationId;
    private String fullName;
    private int heat;
    private List<SchoolAlias> alias;

    private Location location;

    public School id(long id) {
        this.id = id;
        return this;
    }
    public School locationId(long locationId) {
        this.locationId = locationId;
        return this;
    }

    public School heat(int heat) {
        this.heat = heat;
        return this;
    }
    public School fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
    public School alias(List<SchoolAlias> alias) {
        this.alias = alias;
        return this;
    }
    public School location(Location location) {
        this.location = location;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<SchoolAlias> getAlias() {
        return alias;
    }

    public void setAlias(List<SchoolAlias> alias) {
        this.alias = alias;
    }

    public Location getLocation() {
        return location;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
