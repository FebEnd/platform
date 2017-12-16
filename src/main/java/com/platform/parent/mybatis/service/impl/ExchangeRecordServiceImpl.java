package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.ExchangeRecord;
import com.platform.parent.mybatis.dao.ExchangeRecordMapper;
import com.platform.parent.mybatis.service.ExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class ExchangeRecordServiceImpl implements ExchangeRecordService {
    @Autowired
    ExchangeRecordMapper mapper;
    @Override
    public int add(ExchangeRecord record) {
        return this.mapper.add(record);
    }

    @Override
    public int update(ExchangeRecord record) {
        return this.mapper.update(record);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.mapper.deleteByIds(ids);
    }

    @Override
    public List<ExchangeRecord> findExchangeRecordsUnhandled() {
        return this.mapper.findExchangeRecordsUnhandled();
    }
}
