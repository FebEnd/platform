package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
public interface TeacherMapper {
    int add(Teacher teacher);
    int update(Teacher teacher);
    int deleteByIds(String[] ids);
    Teacher findTeacherById(long id);
    List<Teacher> findTeachersByStar(int star);
    List<Teacher> findAllTeachers();
}
