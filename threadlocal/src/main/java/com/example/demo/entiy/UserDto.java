package com.example.demo.entiy;

import lombok.Data;

@Data
public class UserDto {

    private String userName;
    private String pwd;
    private String userId;
    private byte[] info=new byte[1024*1024*2];
}
