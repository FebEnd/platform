package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.FileRecord;
import com.platform.parent.response.file.FileResponse;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface FileRecordService {
    int add(FileRecord record);
    int update(FileRecord record);
    int deleteByIds(String[] ids);
    FileResponse findFileRecordById(long id);
    List<FileResponse> findFileRecordsByCampId(long campId);
    List<FileResponse> findFileRecordsByOwnerId(long ownerId);
}
