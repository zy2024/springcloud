package com.example.producerserver.controller;

import com.example.producerserver.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class MyTestController {

    @Autowired
    private MyService myService;
    @RequestMapping("/getUserName")
    public String getUserName() throws InterruptedException {
        return myService.getUserName();
    }

    @RequestMapping("/getName")
    public String getName() throws InterruptedException {
        return myService.getName();
    }
}
