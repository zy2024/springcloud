package com.example.othermapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OtherUserMapper {

    @Select("select name from student where id=1")
    String getUserName();

}
