package com.example.mapper;

import com.example.util.DataSourceNote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select name from student where id=1")
    @DataSourceNote("db1")
    String getFristDbUserName();
    @DataSourceNote("db2")
    @Select("select name from student where id=2")
    String getSecondDbUserName();

}
