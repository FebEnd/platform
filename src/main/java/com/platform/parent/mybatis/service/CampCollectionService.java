package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.CampCollection;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface CampCollectionService {
    int add(CampCollection collection);
    int update(CampCollection collection);
    int deleteByIds(String[] ids);
    CampCollection findCampCollectionById(long id);
    CampCollection queryCampCollectionByUserIdAndCampId(long userId, long campId);
    List<CampCollection> findCampCollectionsByUserId(long userId);
    List<CampCollection> findCampCollectionsByCampId(long campId);
    //获取userId 的收藏数
    long queryCountByUserId(long userId);
    //获取campId 的收藏数
    long queryCountByCampId(long campId);
}
