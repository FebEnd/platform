package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.FileRecord;
import com.platform.parent.response.file.FileResponse;
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
    FileResponse findFileRecordById(long id);
    List<FileResponse> findFileRecordsByCampId(long campId);
    List<FileResponse> findFileRecordsByOwnerId(long ownerId);
}