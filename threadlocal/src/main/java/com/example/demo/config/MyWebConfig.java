package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Override
   public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new MyIntercepter()).addPathPatterns("/**")
                .excludePathPatterns("**/user/login");
    }
}
