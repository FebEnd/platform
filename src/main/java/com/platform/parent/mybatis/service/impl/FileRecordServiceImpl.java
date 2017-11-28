package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.FileRecord;
import com.platform.parent.mybatis.dao.FileRecordMapper;
import com.platform.parent.mybatis.service.FileRecordService;
import com.platform.parent.response.file.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class FileRecordServiceImpl implements FileRecordService {
    @Autowired
    FileRecordMapper fileRecordMapper;
    @Override
    public int add(FileRecord record) {
        return this.fileRecordMapper.add(record);
    }

    @Override
    public int update(FileRecord record) {
        return this.fileRecordMapper.update(record);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.fileRecordMapper.deleteByIds(ids);
    }

    @Override
    public FileResponse findFileRecordById(long id) {
        return this.fileRecordMapper.findFileRecordById(id);
    }

    @Override
    public List<FileResponse> findFileRecordsByCampId(long campId) {
        return this.fileRecordMapper.findFileRecordsByCampId(campId);
    }

    @Override
    public List<FileResponse> findFileRecordsByOwnerId(long ownerId) {
        return this.fileRecordMapper.findFileRecordsByOwnerId(ownerId);
    }
}
