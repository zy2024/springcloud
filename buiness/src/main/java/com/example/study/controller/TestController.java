package com.example.study.controller;


import com.example.study.entiy.User;
import com.example.study.util.ResponseResult;
import com.example.study.utils.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bus")
public class TestController {

    @Resource
    private RedisUtil redisUtil;
    @RequestMapping(value = "/test")

    public Object test(HttpServletRequest request) throws Exception{
        String token = request.getHeader("token");
        if(token==null){
            return "redirect:/error/unauth.html";
        }
        User user = new User();
        updateUser(user);
        return ResponseResult.success("成功",user);
    }
    private void updateUser(User user){
        user.setPwd("22222");
    }
}
