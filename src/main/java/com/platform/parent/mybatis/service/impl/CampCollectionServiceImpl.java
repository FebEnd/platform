package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.CampCollection;
import com.platform.parent.mybatis.dao.CampCollectionMapper;
import com.platform.parent.mybatis.service.CampCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class CampCollectionServiceImpl implements CampCollectionService {
    @Autowired
    CampCollectionMapper mapper;
    @Override
    public int add(CampCollection collection) {
        return this.mapper.add(collection);
    }

    @Override
    public int update(CampCollection collection) {
        return this.mapper.update(collection);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.mapper.deleteByIds(ids);
    }

    @Override
    public CampCollection findCampCollectionById(long id) {
        return this.mapper.findCampCollectionById(id);
    }

    @Override
    public List<CampCollection> findCampCollectionsByUserId(long userId) {
        return this.mapper.findCampCollectionsByUserId(userId);
    }

    @Override
    public List<CampCollection> findCampCollectionsByCampId(long campId) {
        return this.mapper.findCampCollectionsByCampId(campId);
    }

    @Override
    public long queryCountByUserId(long userId) {
        return queryCountByUserId(userId);
    }

    @Override
    public long queryCountByCampId(long campId) {
        return queryCountByCampId(campId);
    }
}
