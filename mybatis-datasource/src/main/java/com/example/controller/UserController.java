package com.example.controller;

import com.example.entiy.UserDto;
import com.example.mapper.UserInfoMapper;
import com.example.util.UserLocalUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/getUserName")
    public Object getUserName(){
        UserDto userDto = new UserDto();
        userDto.setUserId("1");
        UserLocalUtil.setUser(userDto);
        Map userName = userInfoMapper.loginByUserName("1");
        UserLocalUtil.clear();
        return userName;
    }
}
