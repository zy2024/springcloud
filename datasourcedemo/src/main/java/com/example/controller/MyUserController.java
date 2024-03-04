package com.example.controller;

import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/user")
@RestController
public class MyUserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/getUserDb1")
    public String getUserName(){
        return userMapper.getTypeName();
    }

    @GetMapping("/getUserDb2")
    public String getUserName2(){
        return userMapper.getUserName();
    }
}
