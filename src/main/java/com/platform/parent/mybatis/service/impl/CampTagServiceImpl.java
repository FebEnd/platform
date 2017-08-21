package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.CampTag;
import com.platform.parent.mybatis.dao.CampTagMapper;
import com.platform.parent.mybatis.service.CampTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class CampTagServiceImpl implements CampTagService {
    @Autowired
    CampTagMapper mapper;
    @Override
    public int add(CampTag campTag) {
        return this.mapper.add(campTag);
    }

    @Override
    public int update(CampTag campTag) {
        return this.update(campTag);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.mapper.deleteByIds(ids);
    }

    @Override
    public CampTag findCampTagById(long id) {
        return this.mapper.findCampTagById(id);
    }

    @Override
    public List<CampTag> findCampTagByCampId(long campId) {
        return this.mapper.findCampTagByCampId(campId);
    }

    @Override
    public List<CampTag> findCampTagByTagId(long tagId) {
        return this.mapper.findCampTagByTagId(tagId);
    }
}
