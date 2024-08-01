package com.example.producerserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MyMapper {

    @Select("select name from student where id=1 ")
    public String getUserName();
    @Update("update student set name='小飞' where  id=1")
    public int updateName();
}
