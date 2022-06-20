package com.example.study.config;

import com.example.study.util.JwtUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从session中获取user的信息
        String token = request.getHeader("token");
        Integer integer = JwtUtils.verifyTokenAndGetUserId(token);
        //判断用户是否登录
        if (null==token){
            response.sendRedirect(request.getContextPath()+"/user/error");
            return false;
        }
        return true;
    }
}
