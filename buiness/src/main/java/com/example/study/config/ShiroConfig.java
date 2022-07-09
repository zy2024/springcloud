package com.example.study.config;

import com.example.study.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限配置加载
 * 
 * @author ruoyi
 */
@Configuration
public class ShiroConfig
{

    @Bean
    public ShiroFilterFactoryBean bean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setUnauthorizedUrl("/user/error");
        bean.setLoginUrl("/user/index");
        bean.setSuccessUrl("/user/success");
//        bean.setLoginUrl("/user/toLogin");
        //添加shiro的内置过滤器
        /*
        anon: 无需认证即可访问
        authc: 必须认证才能用
        user: 必须拥有 “记住我” 功能才能用
        perms: 拥有对某个资源的权限才能用
        role: 拥有某个角色权限才能访问
        */

        Map<String,String> filterMap=new HashMap<>();

        //登陆后授权，正常情况下没有授权会跳转到未授权页面
//        filterMap.put("/toAdd","perms[user:add]");
//        filterMap.put("/toUpdate","perms[user:update]");
//        //设置注销过滤器
//        filterMap.put("/logout","logout");
//        filterMap.put("/user/toLogin","anon");
//        filterMap.put("/user/index","anon");
        /**
         *    /** 匹配所有的路径
         *   通过Map集合组成了一个拦截器链 ，自顶向下过滤，一旦匹配，则不再执行下面的过滤
         *   如果下面的定义与上面冲突，那按照了谁先定义谁说了算
         *   所以/** 一定要配置在最后
         *   这里是否要对所有路径进行认证视情况而定，因为一些路由跳转可能在没登陆出现导致出错，所以这里考虑清楚
         **/
        filterMap.put("/user/error","anon");
        filterMap.put("/user/toLogin","anon");
        filterMap.put("/user/index","anon");
        filterMap.put("/user/sendSsm","anon");
        filterMap.put("/user/getInfo","anon");
        filterMap.put("/user/register","anon");
        filterMap.put("/page/**","anon");
        filterMap.put("/**", "authc");

        // 将拦截器链设置到shiro中
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录页面
     //   bean.setLoginUrl("/toLogin");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/index");

        //设置未授权页面
       // bean.setUnauthorizedUrl("/noauth");

        return bean;
    }
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

}
