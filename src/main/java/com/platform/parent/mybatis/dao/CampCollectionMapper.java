package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.CampCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface CampCollectionMapper {
    int add(CampCollection collection);
    int update(CampCollection collection);
    int deleteByIds(String[] ids);
    CampCollection findCampCollectionById(long id);
    CampCollection queryCampCollectionByUserIdAndCampId(@Param("userId") long userId, @Param("campId") long campId);
    List<CampCollection> findCampCollectionsByUserId(long userId);
    List<CampCollection> findCampCollectionsByCampId(long campId);
    //获取userId 的收藏数
    long queryCountByUserId(long userId);
    //获取campId 的收藏数
    long queryCountByCampId(long campId);
}
