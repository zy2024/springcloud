package com.example.producerserver.service;

import com.example.producerserver.mapper.MyMapper;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log
public class MyService {

    @Autowired
    private MyMapper mapper;

    @Transactional
    public String getUserName() throws InterruptedException {
        log.info("开始执行方法:"+Thread.currentThread().getName());
        //模拟长事务操作
        Thread.sleep(30000);
        mapper.updateName();
        log.info("处理成功："+Thread.currentThread().getName());
        //
        return "成功";

    }
    public String getName(){
        return mapper.getUserName();
    }
}
