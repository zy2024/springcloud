package com.example.demo.controller;

import com.example.demo.entiy.UserDto;
import com.example.demo.util.UserLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @GetMapping("/getUser")
    public UserDto login(){
        log.info("开始执行方法");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                UserDto userDto = new UserDto();
                userDto.setUserName("测试");
                userDto.setPwd("111");
                UserLocalUtil.setUser(userDto);
                UserLocalUtil.clear();
            }
        });
        executorService.shutdown();


        return null;
    }

    public static void main(String[] args) {
        //创建个线程池来模拟生产情况
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,3,0l, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(5));
        List<UserDto> list=new ArrayList<>();
        UserDto dto=new UserDto();
        dto.setUserName("张三");
        dto.setPwd("123");
        list.add(dto);
        dto=new UserDto();
        dto.setUserName("李四");
        dto.setPwd("123");
        list.add(dto);
        dto=new UserDto();
        dto.setUserName("王五");
        dto.setPwd("123");
        list.add(dto);
        dto=new UserDto();
        dto.setUserName("赵六");
        dto.setPwd("123");
        list.add(dto);
        dto=new UserDto();
        dto.setUserName("韩七");
        dto.setPwd("123");
        list.add(dto);
        for (UserDto userDto : list) {
            Thread thread = new Thread(() -> {

                if (userDto.getUserName().equals("张三")) {
                    UserLocalUtil.setUser(userDto);
                }
                UserDto user = UserLocalUtil.getUser();
                String name = "";
                if (null != user) {
                    name = user.getUserName();
                }
                log.info("主数据名称为：{},线程用户名为：{}", userDto.getUserName(), name);
            });
            thread.start();

        }
    }



}
