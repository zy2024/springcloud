package com.example.study.mapper;

import com.example.study.entiy.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    int addUser(User user);
    User loginByUserName(@Param("userName") String userName);

}
