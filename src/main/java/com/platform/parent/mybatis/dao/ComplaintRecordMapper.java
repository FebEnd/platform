package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.ComplaintRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
public interface ComplaintRecordMapper {
    int add(ComplaintRecord record);
    int update(ComplaintRecord record);
    int deleteByIds(String[] ids);
    List<ComplaintRecord> findComplaintRecordUnhandled();
}
