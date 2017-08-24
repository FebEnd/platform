package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Teacher;

import java.util.List;

/**
 * Created by tqyao.
 */
public interface TeacherService {
    int add(Teacher teacher);
    int update(Teacher teacher);
    int deleteByIds(String[] ids);
    Teacher findTeacherById(long id);
    List<Teacher> findTeachersByStar(int star);
    List<Teacher> findAllTeachers();
}
