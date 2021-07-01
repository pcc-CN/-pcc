package com.xy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.mapper.StudentInformationMapper;
import com.xy.pojo.StudentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentInformationMapper studentInformationMapper;
    public StudentInformation queryByID(String id){
        QueryWrapper<StudentInformation> wrapper = new QueryWrapper<>();
        wrapper.eq("StudentID",id);

        return  studentInformationMapper.selectOne(wrapper);
    }
}
