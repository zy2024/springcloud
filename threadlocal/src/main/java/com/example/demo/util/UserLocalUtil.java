package com.example.demo.util;

import com.example.demo.entiy.UserDto;
import org.apache.catalina.User;

public class UserLocalUtil {
    private static ThreadLocal<UserDto>   threadLocal=new ThreadLocal<>();

    public static void  setUser(UserDto user){
        threadLocal.set(user);
    }

    public static UserDto getUser(){
      return   threadLocal.get();
    }

    public static void clear(){

        threadLocal.remove();
    }
}
