package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.ExchangeRecord;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface ExchangeRecordService {
    int add(ExchangeRecord record);
    int update(ExchangeRecord record);
    int deleteByIds(String[] ids);
    List<ExchangeRecord> findExchangeRecordsUnhandled();
}
