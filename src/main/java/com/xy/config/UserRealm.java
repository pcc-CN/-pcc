package com.xy.config;

import com.xy.pojo.SystemManager;
import com.xy.pojo.StudentInformation;
import com.xy.pojo.Teacher;
import com.xy.service.ManagerService;
import com.xy.service.StudentService;
import com.xy.service.TeacherService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的UserRea
public class UserRealm extends AuthorizingRealm {

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    ManagerService managerService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权操作===========》");

        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证操作==============》");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接数据库
        System.out.println(userToken.getUsername());
        StudentInformation studentInformation = studentService.queryByID(userToken.getUsername());
        Teacher teacher = teacherService.queryByID(userToken.getUsername());
        SystemManager systemManager = managerService.queryByID(userToken.getUsername());


        System.out.println(studentInformation + "" + teacher + "" + systemManager + "");
        if (studentInformation == null && teacher == null && systemManager == null) {
            return null;
        } else if (studentInformation != null) {
            return new SimpleAuthenticationInfo("", studentInformation.getPassword(), "");
        } else if (teacher != null) {
            return new SimpleAuthenticationInfo("", teacher.getPassword(), "");
        } else return new SimpleAuthenticationInfo("", systemManager.getPassword(), "");


    }
}

