package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.CampDetail;

/**
 * Created by tqyao.
 */
public interface CampDetailService {
    int add(CampDetail campDetail);
    int update(CampDetail campDetail);
    int deleteByIds(String[] ids);
}
