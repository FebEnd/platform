package com.platform.parent.response.school;

import com.platform.parent.mybatis.bean.School;

import java.util.List;

/**
 * Created by tqyao.
 */
public class SearchResponse {
    private int status;
    private List<School> schools;

    public SearchResponse(int status, List<School> schools) {
        this.status = status;
        this.schools = schools;
    }

    public List<School> getSchools() {
        return schools;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }
}
