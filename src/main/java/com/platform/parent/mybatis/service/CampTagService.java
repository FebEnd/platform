package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.CampTag;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface CampTagService {
    int add(CampTag campTag);
    int update(CampTag campTag);
    int deleteByIds(String[] ids);
    CampTag findCampTagById(long id);
    List<CampTag> findCampTagByCampId(long campId);
    List<CampTag> findCampTagByTagId(long tagId);
}
