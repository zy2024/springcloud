package com.example.mapper;

import com.example.util.DynamicDB;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select name from student where id=1")
    @DynamicDB("db1")
    String getTypeName();
    @DynamicDB("db2")
    @Select("select name from student where id=2")
    String getUserName();

}
