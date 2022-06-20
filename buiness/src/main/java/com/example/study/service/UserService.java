package com.example.study.service;

import com.example.study.Log.Log;
import com.example.study.entiy.User;
import com.example.study.mapper.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserService  {

    @Resource
    private UserDao userDao;

    public User  loginByUserName(String userName){

        return userDao.loginByUserName(userName);
    }

    public int register(User user){

        return userDao.addUser(user);
    }
}
