package com.example.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RedisMasterApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private RedisTemplate redisTemplate;
    //这个是我自己写的一个mapper，大家可以换成自己写的类


}

