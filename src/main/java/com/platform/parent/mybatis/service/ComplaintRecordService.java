package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.ComplaintRecord;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface ComplaintRecordService {
    int add(ComplaintRecord record);
    int update(ComplaintRecord record);
    int deleteByIds(String[] ids);
    List<ComplaintRecord> findComplaintRecordUnhandled();
}
