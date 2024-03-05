package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.othermapper.OtherUserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/user")
@RestController
public class MyUserController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private OtherUserMapper otherUserMapper;

    @GetMapping("/getUserName")
    public String getUserName(){
        return userMapper.getUserName()+"-"+otherUserMapper.getUserName();
    }

}
