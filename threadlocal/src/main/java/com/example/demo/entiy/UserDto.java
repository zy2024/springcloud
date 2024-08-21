package com.example.demo.entiy;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

@Data
public class UserDto {

    private String userName;
    private String pwd;
    private String userId;
    //private byte[] info=new byte[1024*1024*2];
//    public static void main(String[] args) throws  Exception {
//      Map<String,String> map=new HashMap<>();
//      map.put("userName","nnnn");
//      ObjectMapper objectMapper=new ObjectMapper();
//        String s = objectMapper.writeValueAsString(map);
//        System.out.printf("加密："+ DigestUtils.md5(s));
//        System.out.printf("加密："+ DigestUtils.md5(map.toString()));
//    }

    public static void main(String[] args) {
        Map map=null;
        List<Map> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            map=new HashMap();
            map.put("1","1"+i);
            list.add(map);
        }
        System.out.printf("");
    }
}
