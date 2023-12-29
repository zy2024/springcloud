package com.example.demo.controller;

import com.example.demo.entiy.UserDto;
import com.example.demo.util.UserLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @GetMapping("/getUser")
    public UserDto login(){
        log.info("开始执行方法");

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDto userDto = new UserDto();
                userDto.setUserName("测试");
                userDto.setPwd("111");
                UserLocalUtil.setUser(userDto);
            }
        }).start();

        return UserLocalUtil.getUser();
    }
}
