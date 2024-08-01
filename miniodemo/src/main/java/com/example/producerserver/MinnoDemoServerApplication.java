package com.example.producerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class MinnoDemoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinnoDemoServerApplication.class, args);
    }


}
