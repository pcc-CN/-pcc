package com.xy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.mapper.*;
import com.xy.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class AddController {
//学院操作
    @Autowired
    CollegeMapper collegeMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseSelectionMapper courseSelectionMapper;
    @Autowired
    TeacherStartListMapper teacherStartListMapper;
    @Autowired
    PreCourseMapper preCourseMapper;
    @Autowired
    StudentInformationMapper studentInformationMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CourseStartListMapper courseStartListMapper;
    @Autowired
    CourseListMapper courseListMapper;


//    @GetMapping("/toAddCollege")
//    public String toAddCollege(){
//        return "repositroy/add";
//    }
//
//    @PostMapping("/AddCollege")
//    public String AddCollege(College college){
//        System.out.println("save=====>"+college);
//        collegeMapper.insert(college);
//        return "redirect:/college";
//    }

    //学生添加课程
    @PostMapping("/AddCourse/{id}")
    public String AddCourse( @PathVariable("id")String id, CourseStartList courseStartList,Model model){
        //待添加选课表元组
        CourseSelection courseSelection = new CourseSelection(id,courseStartList.getSerialNumber()
                ,courseStartList.getCourseID(),courseStartList.getSemester(),courseStartList.getYear(),courseStartList.getCourseName(),
                courseStartList.getTimeid());

        //根据学生id找出学生已有的选课情况
        QueryWrapper<CourseSelection> wrapper0 = new QueryWrapper<>();
        wrapper0.eq("StudentID",id);
        List<CourseSelection> courseSelections = courseSelectionMapper.selectList(wrapper0);
        //先修课检查
        QueryWrapper<PreCourse> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("CourseID",courseStartList.getCourseID());
        List<PreCourse> preCourses = preCourseMapper.selectList(wrapper1);

        if(!preCourses.isEmpty()){
            model.addAttribute("msg","请先选修相应先修课"+preCourses);
            return "student/Success";
        }

        //检查是否重复添加
        QueryWrapper<CourseSelection> wrapper = new QueryWrapper<>();
        wrapper.eq("CourseID",courseStartList.getCourseID())
               .eq("StudentID",id);

       if(courseSelectionMapper.selectOne(wrapper)==null){
           //时间冲突检查
           for(CourseSelection c:courseSelections){
               if(c.getTimeid()  == courseStartList.getTimeid()){
                   model.addAttribute("msg","选课时间冲突！请同学调整课程安排");
                   return "student/Success";
               }
           }
            courseSelectionMapper.insert(courseSelection);
            QueryWrapper<StudentInformation> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("studentID",courseSelection.getStudentID());

            QueryWrapper<TeacherStartList> wrapper3 = new QueryWrapper<>();
            wrapper3.eq("CourseID",courseSelection.getCourseID());

            StudentInformation studentInformation = studentInformationMapper.selectOne(wrapper2);
            TeacherStartList teacherStartList = teacherStartListMapper.selectOne(wrapper3);

            CourseList courseList = new CourseList(teacherStartList.getFacultyNumber(),studentInformation.getStudentID()
            ,courseSelection.getSerialNumber(),courseStartList.getCourseID(),courseStartList.getSemester(),
                    courseStartList.getYear(),courseSelection.getCourseName(),studentInformation.getName(),
                    0);

           courseListMapper.insert(courseList);
            model.addAttribute("msg","添加成功！");
            model.addAttribute("id",id);
            return "student/Success";
        }
        else{
            model.addAttribute("msg","请勿重复添加！！");
            model.addAttribute("id",id);
            return "student/Success";
        }

    }
    @RequestMapping("/returnSelectCourse/{id}")
    public String returnSelectCourse(@PathVariable("id") String id){

        return "redirect:/SelectCourse/"+id;
    }


    //教师跳转到添加新课程页面
    @RequestMapping("/toAddStartCourse/{id}")
    public String toaddStartCourse(@PathVariable("id")String id,Model model){
        model.addAttribute("id",id);
        return "teacher/add";
    }
    //教师完成课程添加
    @PostMapping("/AddStartCourse/{id}")
    public String addStartCourse(@PathVariable("id")String id,TeacherStartList teacherStartList,RedirectAttributes redirectAttributes){
        teacherStartListMapper.insert(teacherStartList);
        redirectAttributes.addFlashAttribute("id",id);
        Course c = new Course(teacherStartList.getCourseID(),teacherStartList.getSerialNumber(),teacherStartList.getName(),
                teacherStartList.getCollegeName(),0,teacherStartList.getFacultyNumber());
        courseMapper.insert(c);
        return "redirect:/Teacher.main";
    }
    //管理员跳转到添加新学院页面
    @RequestMapping("/toAddCollege/{id}")
    public String toAddCollege(@PathVariable("id")String id,Model model){
    model.addAttribute("id",id);
    return "manager/addCollege";
    }
    //管理员完成学院添加
    @PostMapping("/AddCollege/{id}")
    public String addCollege(@PathVariable("id")String id,College college,RedirectAttributes redirectAttributes){
        collegeMapper.insert(college);
        redirectAttributes.addFlashAttribute("id",id);
        return "redirect:/Manager.main";
    }
    //管理员跳转到添加学生
    @RequestMapping("/toAddStudent/{id}")
    public String toAddStudent(@PathVariable("id")String id,Model model){
        model.addAttribute("id",id);
        return "manager/addStudent";
    }
    //管理员完成学生添加
    @RequestMapping("/AddStudent/{id}")
    public String addStudent(@PathVariable("id")String id,StudentInformation studentInformation,RedirectAttributes redirectAttributes){
        studentInformationMapper.insert(studentInformation);
        redirectAttributes.addFlashAttribute("id",id);
        return "redirect:/Manager.main";
    }
    //管理员跳转到添加教师
    @RequestMapping("/toAddTeacher/{id}")
    public String toAddTeacher(@PathVariable("id")String id,Model model){
        model.addAttribute("id",id);
        return "manager/addTeacher";
    }
    //管理员完成教师添加
    @RequestMapping("/AddTeacher/{id}")
    public String AddTeacher(@PathVariable("id")String id,Teacher teacher,RedirectAttributes redirectAttributes){
        teacherMapper.insert(teacher);
        redirectAttributes.addFlashAttribute("id",id);
        return "redirect:/Manager.main";
    }
    //管理员跳转到教师开课意向表
    @RequestMapping("/toTeacherStart/{id}")
    public String toTeacherStart(@PathVariable("id")String id,Model model){
        model.addAttribute("id",id);
        List<TeacherStartList> teacherStartLists = teacherStartListMapper.selectList(null);
        model.addAttribute("teacherStartLists",teacherStartLists);
        return "manager/SelectTeacherStart";
    }
    //管理员增加开课表
    @RequestMapping("/toCourseStart/{id}")
    public String toCourseStart(@PathVariable("id")String id,Model model,TeacherStartList teacherStartList){
        model.addAttribute("id",id);
        model.addAttribute("teacherStartList",teacherStartList);
        return "manager/add";
    }
    //管理员完成开课
    @RequestMapping("/AddCourseStart/{id}")
    public String AddCourseStart(@PathVariable("id")String id,CourseStartList courseStartList,RedirectAttributes redirectAttributes){
        System.out.println(courseStartList);
        courseStartListMapper.insert(courseStartList);
        redirectAttributes.addFlashAttribute("id",id);
        return "redirect:/Manager.main";
    }
    //教师跳转到添加成绩页面
    @RequestMapping("/toAddGrades/{id}")
    public String toAddGrades(@PathVariable("id")String id,CourseList courseList,Model model){
        model.addAttribute(courseList);
        System.out.println(courseList);
        return "teacher/toAddGrades";
    }
    //教师添加成绩
    @RequestMapping("/Addgrades/{id}")
    public String AddGrades(@PathVariable("id")String id,CourseList courseList,Model model){
        System.out.println("======================>>>"+courseList);
        QueryWrapper<CourseList> wrapper = new QueryWrapper<>();
        wrapper.eq("FacultyNumber",courseList.getFacultyNumber())
                .eq("StudentID",courseList.getStudentID())
                .eq("SerialNumber",courseList.getSerialNumber())
                .eq("CourseID",courseList.getCourseID());

        courseListMapper.delete(wrapper);
        courseListMapper.insert(courseList);

        model.addAttribute("id",id);
        return "redirect:/toCourseList/{id}";
    }


}
