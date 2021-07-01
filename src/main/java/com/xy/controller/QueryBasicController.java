package com.xy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.mapper.*;
import com.xy.pojo.*;
import com.xy.pojo.vo.CourseSelection_Time_Location;
import com.xy.pojo.vo.CourseStartListTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;

@Controller
public class QueryBasicController {

    @Autowired
    CollegeMapper collegeMapper;

    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseStartListMapper courseStartListMapper;
    @Autowired
    CourseSelectionMapper courseSelectionMapper;
    @Autowired
    TeacherStartListMapper teacherStartListMapper;
    @Autowired
    TimeMapper timeMapper;
    @Autowired
    LocationMapper locationMapper;
    @Autowired
    CourseStartListTimeMapper courseStartListTimeMapper;
    @Autowired
    CourseSelection_Time_LocationMapper courseSelection_time_locationMapper;
    @Autowired
    CourseListMapper courseListMapper;
    @Autowired
    StudentInformationMapper studentInformationMapper;
    @Autowired
    TeacherMapper teacherMapper;
    //返回学生首页
    @GetMapping("/StuMain/{id}")
    public String toStuIndex(RedirectAttributes redirectAttributes,@PathVariable("id")String id){

        redirectAttributes.addFlashAttribute("id",id);
        return "redirect:/Student.main";
    }
    //返回教师首页
    @GetMapping("/TeaMain/{id}")
    public String toTeaIndex(RedirectAttributes redirectAttributes,@PathVariable("id")String id){
        redirectAttributes.addFlashAttribute("id",id);
        return "redirect:/Teacher.main";
    }


    //返回管理员首页

    @RequestMapping("/ManagerMain/{id}")
    public String toManagerMain(@PathVariable("id")String id,RedirectAttributes redirectAttributes){
        System.out.println("===================>>"+id);
       redirectAttributes.addFlashAttribute("id",id);
        return "redirect:/Manager.main";
    }

    //学生：跳转到成绩查询，可以直接在页面看到个人成绩
    @GetMapping("/grades/{id}")
    public String queryGrades(Model model, @PathVariable("id")String id){

        QueryWrapper<CourseList> wrapper = new QueryWrapper<>();
        wrapper.eq("studentID",id);
        Collection<CourseList> courseLists = courseListMapper.selectList(wrapper);
        model.addAttribute("transcripts",courseLists);

        return "student/grades";
        }
    //展示开课表
    @GetMapping("/SelectCourse/{id}")
    public String toSelectCourse(@PathVariable("id") String id ,Model model){

        List<CourseStartList> courseStartLists  = courseStartListMapper.selectList(null);
        List<CourseStartListTime> courseStartListTimes = courseStartListTimeMapper.getCourseStartListTime();
        model.addAttribute("courseStartLists",courseStartListTimes);
        model.addAttribute("id",id);

        return "student/selectCourse";
    }
    //展示学生课表
    @GetMapping("/QueryCourse/{id}")
    public String toCourseTable(@PathVariable("id")String id,Model model){
        QueryWrapper<CourseSelection_Time_Location> wrapper = new QueryWrapper<>();
        wrapper.eq("StudentID",id);
       List<CourseSelection_Time_Location> courseSelections = courseSelection_time_locationMapper.getcourseSelectionTL(id);
       for(CourseSelection_Time_Location c:courseSelections){
           System.out.println(c.getCourseName());
       }
       model.addAttribute("CourseTables",courseSelections);
        return "student/CourseTable";
    }
    //教师：跳转到开课页面,包括显示已有课程，编辑、删除
    @RequestMapping("/toStartCourse/{id}")
    public String toStartCourse(@PathVariable("id")String id,Model model){

        QueryWrapper<TeacherStartList> wrapper = new QueryWrapper<>();
        wrapper.eq("FacultyNumber",id);
        List<TeacherStartList> teacherStartLists = teacherStartListMapper.selectList(wrapper);
        model.addAttribute(teacherStartLists);
        model.addAttribute("id",id);
        return "teacher/startCourse";
    }
    //教师跳转到课程名单，查看自己学生的情况
    @RequestMapping("/toCourseList/{id}")
    public String toCourseList(@PathVariable("id")String id,Model model){
        QueryWrapper<CourseList> wrapper = new QueryWrapper<>();
        wrapper.eq("FacultyNumber",id);
        List<CourseList> courseLists = courseListMapper.selectList(wrapper);
        model.addAttribute("CourseLists",courseLists);

        return "teacher/selectCourseList";
    }
    //管理员查看开课情况
    @RequestMapping("/toMStartCourse/{id}")
    public String toMStartCourse(@PathVariable("id")String id,Model model){
        List<CourseStartList> courseStartLists = courseStartListMapper.selectList(null);
        model.addAttribute(courseStartLists);
        return "manager/showStartCourse";
    }
    //管理员查看学生
    @RequestMapping("/toMStudent/{id}")
    public String toMStudent(@PathVariable("id")String id,Model model){
        List<StudentInformation> studentInformations = studentInformationMapper.selectList(null);
        model.addAttribute("students",studentInformations);
        return "manager/toMStudent";
    }
    //管理员查看教师
    @RequestMapping("/toMTeacher/{id}")
    public String toMTeacher(@PathVariable("id")String id,Model model){
        List<Teacher> teachers = teacherMapper.selectList(null);
        model.addAttribute("teachers",teachers);
        return "manager/toMTeacher";
    }

}
