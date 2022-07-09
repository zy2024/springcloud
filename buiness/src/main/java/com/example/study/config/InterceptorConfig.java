package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//定义此类为配置类
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	//addPathPatterns拦截的路径
        String[] addPathPatterns = {
                "/user/*"
        };
        //excludePathPatterns排除的路径
        String[] excludePathPatterns = {
                "/user/sendSsm",
                "/user/toLogin",
                "/user/index",
                "/page/**",
                "/user/error","/user/register"
        };
        //创建用户拦截器对象并指定其拦截的路径和排除的路径
        registry.addInterceptor(new UserInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
