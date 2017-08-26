package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface BalanceMapper {
    int add(Balance balance);
    int update(Balance balance);
    int deleteByIds(String[] ids);
    Balance findBalanceById(long id);

    /**
     * 查找从start 到end之间的balance记录
     * @param start
     * @param end
     * @return
     */
    List<Balance> findBalanceByTime(Timestamp start, Timestamp end);

    /**
     * 查找type的balance记录，即某个训练营的相关balance记录
     * @param type
     * @return
     */
    List<Balance> findBalanceByType(long type);

}
