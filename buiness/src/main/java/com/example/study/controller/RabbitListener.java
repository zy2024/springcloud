package com.example.study.controller;

import com.google.common.base.Utf8;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@org.springframework.amqp.rabbit.annotation.RabbitListener(queues = {"ssm"})
public class RabbitListener {

    @RabbitHandler
    public void getSsmMassage(String msg){
        System.out.printf("收到得短信是"+msg);
    }
}
