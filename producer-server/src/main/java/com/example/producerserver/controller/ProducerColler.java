package com.example.producerserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/producer")
public class ProducerColler {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/getUserName")

    public String getUserName() throws InterruptedException {
        System.out.printf("生产者接到请求");
       // Thread.sleep(3000);
      //  String userName = restTemplate.getForObject("http://customer-service/customer/getUserName", String.class);
        return "我是生产者：我拿到了返回值:";
    }

}
