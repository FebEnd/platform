package com.platform.parent.response.school;

import com.platform.parent.mybatis.bean.School;

import java.util.List;

/**
 * Created by tqyao.
 */
public class SearchResponse {
    private String status;
    private List<School> schools;

    public SearchResponse(String status, List<School> schools) {
        this.status = status;
        this.schools = schools;
    }

    public List<School> getSchools() {
        return schools;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }
}
