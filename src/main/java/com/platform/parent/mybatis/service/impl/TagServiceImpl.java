package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Tag;
import com.platform.parent.mybatis.dao.TagMapper;
import com.platform.parent.mybatis.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Service
public class TagServiceImpl implements TagService{
    @Autowired
    TagMapper mapper;

    @Override
    public int add(Tag tag) {
        return this.mapper.add(tag);
    }

    @Override
    public int update(Tag tag) {
        return this.mapper.update(tag);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.mapper.deleteByIds(ids);
    }

    @Override
    public Tag findTagById(long id) {
        return this.mapper.findTagById(id);
    }

    @Override
    public List<Tag> findTagsByUserId(long id) {
        return this.mapper.findTagsByUserId(id);
    }

    @Override
    public List<Tag> findTagsByCampId(long id) {
        return this.mapper.findTagsByCampId(id);
    }

    @Override
    public Tag findTagByContent(String content) {
        return this.mapper.findTagByContent(content);
    }
}
