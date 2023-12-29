package com.example.producerserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerColler {

    @GetMapping("/getUserName")
    public String getUserName() throws InterruptedException {
        System.out.printf("消费者者接到请求");

      //  Thread.sleep(6000);
        return "我是消费者者:我叫李白";
    }

}
