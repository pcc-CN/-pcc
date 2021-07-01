package com.xy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.mapper.CollegeMapper;
import com.xy.mapper.CourseStartListMapper;
import com.xy.mapper.TeacherStartListMapper;
import com.xy.pojo.College;
import com.xy.pojo.CourseStartList;
import com.xy.pojo.TeacherStartList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UpdateController {

    @Autowired
    CollegeMapper collegeMapper;
    @Autowired
    TeacherStartListMapper teacherStartListMapper;
    //用于处理前端传回更新数据
    private College collegeUpdate;
    //去学院的修改页面
//    @GetMapping("/college/{name}")
//    public String toUpdateCollege(@PathVariable("name")String name,Model model){
//        //查出原来的数据
//        QueryWrapper<College> wrapper = new QueryWrapper<>();
//        wrapper.eq("name",name);
//        College college = collegeMapper.selectOne(wrapper);
//        collegeUpdate = college;
//        model.addAttribute("college",college);
//        return "update";
//    }

    //修改学院信息,如果学院名以及专业都被修改则添加新的学院
    @PostMapping("/updateCollege")
    public String updateCollege(College college){

//        QueryWrapper<College> Wrapper1 = new QueryWrapper<>();
//        QueryWrapper<College> Wrapper2 = new QueryWrapper<>();
//        Wrapper1.eq("profession",college.getProfession());
//        Wrapper2.eq("name",college.getName());
//        if(collegeMapper.selectOne(Wrapper1)==null&&collegeMapper.selectOne(Wrapper2)==null){
//            collegeMapper.insert(college);
//            return "redirect:/college";
//        }
//        else {
//            QueryWrapper<College> Wrapper3 = new QueryWrapper<>();
//            QueryWrapper<College> Wrapper4 = new QueryWrapper<>();
//            Wrapper3.eq("name",college.getName());
//            Wrapper4.eq("profession",college.getProfession());
//            collegeMapper.update(college,Wrapper3);
//            collegeMapper.update(college,Wrapper4);
        QueryWrapper<College> Wrapper = new QueryWrapper<>();
        Wrapper.eq("name",collegeUpdate.getName());
        collegeMapper.update(college,Wrapper);
            return "redirect:/college";
        }
    //去教室开课表的修改页面
    @PostMapping("/changeStartCourse/{id}")
    public String updateStartCourse(@PathVariable("id")String id,Model model){
        //查出原来的数据
        QueryWrapper<TeacherStartList> wrapper = new QueryWrapper<>();
        wrapper.eq("CourseID",id);
        TeacherStartList teacherStartList = teacherStartListMapper.selectOne(wrapper);
        model.addAttribute("teacherStartList",teacherStartList);
        return "teache/update";
    }


    //去修改开课表的页面
}

