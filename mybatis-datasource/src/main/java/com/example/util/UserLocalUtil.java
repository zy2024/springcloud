package com.example.util;


import com.example.entiy.UserDto;

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
