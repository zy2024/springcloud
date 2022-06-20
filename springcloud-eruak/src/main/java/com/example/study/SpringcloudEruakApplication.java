package com.example.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringcloudEruakApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEruakApplication.class, args);
    }

}
