package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.ComplaintRecord;
import com.platform.parent.mybatis.dao.ComplaintRecordMapper;
import com.platform.parent.mybatis.service.ComplaintRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class ComplaintRecordServiceImpl implements ComplaintRecordService{
    @Autowired
    ComplaintRecordMapper mapper;
    @Override
    public int add(ComplaintRecord record) {
        return this.mapper.add(record);
    }

    @Override
    public int update(ComplaintRecord record) {
        return this.mapper.update(record);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.mapper.deleteByIds(ids);
    }

    @Override
    public List<ComplaintRecord> findComplaintRecordUnhandled() {
        return this.mapper.findComplaintRecordUnhandled();
    }
}
