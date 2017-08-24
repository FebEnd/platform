package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Teacher;
import com.platform.parent.mybatis.dao.TeacherMapper;
import com.platform.parent.mybatis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tqyao.
 */
@Component
@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public int add(Teacher teacher) {
        return this.teacherMapper.add(teacher);
    }

    @Override
    public int update(Teacher teacher) {
        return this.teacherMapper.update(teacher);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.teacherMapper.deleteByIds(ids);
    }

    @Override
    public Teacher findTeacherById(long id) {
        return this.teacherMapper.findTeacherById(id);
    }

    @Override
    public List<Teacher> findTeachersByStar(int star) {
        return this.teacherMapper.findTeachersByStar(star);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return this.teacherMapper.findAllTeachers();
    }
}
