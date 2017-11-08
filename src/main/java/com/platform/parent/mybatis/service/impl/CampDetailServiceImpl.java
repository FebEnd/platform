package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.CampDetail;
import com.platform.parent.mybatis.dao.CampDetailMapper;
import com.platform.parent.mybatis.service.CampDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tqyao.
 */
@Service
public class CampDetailServiceImpl implements CampDetailService {
    @Autowired
    CampDetailMapper mapper;
    @Override
    public int add(CampDetail campDetail) {
        return this.mapper.add(campDetail);
    }

    @Override
    public int update(CampDetail campDetail) {
        return this.mapper.update(campDetail);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.mapper.deleteByIds(ids);
    }
}
