package com.xy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.mapper.*;
import com.xy.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class LoginController {

@Autowired
StudentInformationMapper studentInformationMapper;
@Autowired
CourseSelectionMapper selectionMapper;
@Autowired
CollegeMapper collegeMapper;
@Autowired
CourseListMapper courseListMapper;
@Autowired
CourseMapper courseMapper;
//    @RequestMapping("/user/login")
//    public String login(@RequestParam("inputEmail")String inputEmail, @RequestParam("inputPassword") String inputPassword
//            , Model model, HttpSession session){
//        if(!StringUtils.isEmpty(inputEmail) && "1321899364".equals(inputPassword)){
//            session.setAttribute("loginUser",inputEmail);
//
//            return "redirect:/Student.main";
//
//        }else{
//            model.addAttribute("msg","用户名或者密码错误");
//            return "index";
//        }
//    }

    @RequestMapping("/user/login")
    public String login2(@RequestParam("ID") String id,@RequestParam("password") String password, Model model,
                         HttpSession session,RedirectAttributes redirectAttributes){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(id, password);
        try {
              subject.login(token);//执行登录的方法
              session.setAttribute("loginUser",id);
              redirectAttributes.addFlashAttribute("id",id);

            //用contain语法判断
            if(id.contains("t")){
                return "redirect:/Teacher.main";
            }
            else if(id.contains("m"))
            {
                return "redirect:/Manager.main";
            }
            QueryWrapper<CourseList> wrapper0 = new QueryWrapper<>();
            wrapper0.eq("StudentID",id);
            List<CourseList> courseLists = courseListMapper.selectList(wrapper0);
            QueryWrapper<Course> wrapper1 = new QueryWrapper<>();

            float credits=0;
            float grades = 0;
            float mul = 0;
            float Grade_point = 0;
            for(CourseList c:courseLists){
                wrapper1.eq("CourseID",c.getCourseID());
                Course course = courseMapper.selectOne(wrapper1);
                credits+= course.getCredit();
                grades += c.getGrades();
                mul += credits*grades;
            }
            if(grades!=0 && mul!=0){
             Grade_point = mul/grades;}
//            model.addAttribute("Grade_point",Grade_point);
            redirectAttributes.addFlashAttribute("Grade_point",Grade_point);
            return "redirect:/Student.main";
        } catch (UnknownAccountException e) {//用户名不存在
            model.addAttribute("msg", "用户名错误,点击重新登录");
            return "error/User";
        } catch (IncorrectCredentialsException e) {//密码错误
            model.addAttribute("msg", "密码错误，点击重新登录");
            return "error/password";
        }

    }
    //跳转到学生主页面
    @RequestMapping("/Student.main")
    public String toStudentMain(Model model){
        return "StuMainPage";
    }
    //跳转到教师页面
    @RequestMapping("/Teacher.main")
    public String toTeacherMain(){return "TeaMainPage";}
    //跳转到管理员页面
    @RequestMapping("/Manager.main")
    public String toManagerMain(Model model){
        model.addAttribute("colleges",collegeMapper.selectList(null));
        return "ManMainPage";
    }
    @RequestMapping("/index")
    public String error(){
        return "redirect:/";
    }
}
