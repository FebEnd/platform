package com.platform.parent.response.camp;

import java.util.List;

/**
 * Created by tqyao.
 */
public class CampDetail {
    private long campId;
    private String title, announcement;
    private List<UserMini> teachers;
    private List<UserMini> members;
    private List<UserMini> observers;
    private List<UserMini> managers;
    private List<UserMini> noneMembers;

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public List<UserMini> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<UserMini> teachers) {
        this.teachers = teachers;
    }

    public List<UserMini> getMembers() {
        return members;
    }

    public void setMembers(List<UserMini> members) {
        this.members = members;
    }

    public List<UserMini> getObservers() {
        return observers;
    }

    public void setObservers(List<UserMini> observers) {
        this.observers = observers;
    }

    public List<UserMini> getManagers() {
        return managers;
    }

    public void setManagers(List<UserMini> managers) {
        this.managers = managers;
    }

    public List<UserMini> getNoneMembers() {
        return noneMembers;
    }

    public void setNoneMembers(List<UserMini> noneMembers) {
        this.noneMembers = noneMembers;
    }
}
