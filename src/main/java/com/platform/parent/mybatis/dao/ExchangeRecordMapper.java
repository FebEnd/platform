package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.ExchangeRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
public interface ExchangeRecordMapper {
    int add(ExchangeRecord record);
    int update(ExchangeRecord record);
    int deleteByIds(String[] ids);
    List<ExchangeRecord> findExchangeRecordsUnhandled();
}
