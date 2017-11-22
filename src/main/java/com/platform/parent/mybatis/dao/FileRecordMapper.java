package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.FileRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
public interface FileRecordMapper {
    int add(FileRecord record);
    int update(FileRecord record);
    int deleteByIds(String[] ids);
    FileRecord findFileRecordById(long id);
    List<FileRecord> findFileRecordsByCampId(long campId);
    List<FileRecord> findFileRecordsByOwnerId(long ownerId);
}
