package com.xy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.mapper.TeacherMapper;
import com.xy.pojo.StudentInformation;
import com.xy.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;
    public Teacher queryByID(String id){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("FacultyNumber",id);

        return  teacherMapper.selectOne(wrapper);
    }
}
