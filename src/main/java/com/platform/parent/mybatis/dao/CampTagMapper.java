package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.CampTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface CampTagMapper {
    int add(CampTag campTag);
    int update(CampTag campTag);
    int deleteByIds(String[] ids);
    CampTag findCampTagById(long id);
    List<CampTag> findCampTagByCampId(long campId);
    List<CampTag> findCampTagByTagId(long tagId);
}
