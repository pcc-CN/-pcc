package com.xy.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
            anon:无需认证即可访问
            authc:必须认证
            user:必须拥有记住我功能才能用
            perms:拥有对某个资源的权限才能访问
            role:拥有某个角色权限
         */
//        Map<String, String> filterMap = new LinkedHashMap<>();
//
//        filterMap.put("/college","authc");
//
//        bean.setFilterChainDefinitionMap(filterMap);
//        //设置登录请求
//        bean.setLoginUrl("/");


        return bean;
    }

    //DefaultWebSecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象,需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
//
//    //整合shiroDialect:用来shiro thymeleaf
//    public ShiroDialec
}
