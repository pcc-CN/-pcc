package com.xy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.mapper.CollegeMapper;
import com.xy.mapper.CourseListMapper;
import com.xy.mapper.CourseSelectionMapper;
import com.xy.mapper.TeacherStartListMapper;
import com.xy.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DeleteController {
    @Autowired
    CollegeMapper collegeMapper;
    @Autowired
    CourseSelectionMapper courseSelectionMapper;
    @Autowired
    TeacherStartListMapper teacherStartListMapper;
    @Autowired
    CourseListMapper courseListMapper;

    //删除学院
    @GetMapping("/deleteCollege/{name}")
    public String deleteCollege(@PathVariable("name") String name,College college ){
            QueryWrapper<College> wrapper = new QueryWrapper<>();
            wrapper.eq("name",college.getName());
            collegeMapper.delete(wrapper);
            return "redirect:/college";
    }
    //删除课程
    @PostMapping("/DeleteCourse/{id}")
    public String deleteCourse(@PathVariable("id")String id , CourseStartList courseStartList){
        QueryWrapper<CourseSelection> wrapper = new QueryWrapper<>();
         wrapper.eq("StudentID",id)
                .eq("SerialNumber",courseStartList.getSerialNumber())
                .eq("CourseID",courseStartList.getCourseID());
        courseSelectionMapper.delete(wrapper);
        QueryWrapper<CourseList> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("StudentID",id)
                .eq("SerialNumber",courseStartList.getSerialNumber())
                .eq("CourseID",courseStartList.getCourseID());
        courseListMapper.delete(wrapper1);
        return "redirect:/SelectCourse/"+id;
    }
    //删除老师的开的课程
    @PostMapping("/deleteStartCourse")
    public String deleteStartCourse(TeacherStartList teacherStartList, RedirectAttributes redirectAttributes){

        QueryWrapper<TeacherStartList> wrapper = new QueryWrapper<>();
        wrapper.eq("FacultyNumber",teacherStartList.getFacultyNumber())
               .eq("CourseID",teacherStartList.getCourseID());
        teacherStartListMapper.delete(wrapper);
        redirectAttributes.addFlashAttribute("id",teacherStartList.getFacultyNumber());
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^"+teacherStartList.getFacultyNumber());
        return "redirect:/toStartCourse/"+teacherStartList.getFacultyNumber();
    }
}
