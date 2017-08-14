package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Component
@Mapper
public interface TagMapper {
    int add(Tag tag);
    int update(Tag tag);
    int deleteByIds(String[] ids);
    Tag findTagById(long id);
    //find tags associated with the user id
    List<Tag> findTagsByUserId(long id);
    //find tags associated with the camp id
    List<Tag> findTagsByCampId(long id);
    Tag findTagByContent(String content);

}
