package com.example.demo.config;

import com.example.demo.entiy.UserDto;
import com.example.demo.util.UserLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class MyIntercepter implements HandlerInterceptor {
    /**
     * 处理请求前
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info("处理请求前");
        //模拟用户登录获取用户信息
        UserDto user = new UserDto();
        user.setUserName("张三");
        user.setPwd("1234");
        user.setUserId("222");
        UserLocalUtil.setUser(user);
        return true;
    }

    /**
     * 处理请求
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                          @Nullable ModelAndView modelAndView) throws Exception {
       log.info("处理请求");
    }

    /**
     * 处理请求后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
  public   void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                         @Nullable Exception ex) throws Exception {

       // UserLocalUtil.clear();
        log.info("处理请求后");
    }
}
