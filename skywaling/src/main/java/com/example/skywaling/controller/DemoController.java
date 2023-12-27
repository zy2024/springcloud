package com.example.skywaling.controller;

import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.annotation.Target;
import java.util.Map;

@RequestMapping("/test")
@RestController
public class DemoController {

    @RequestMapping("/info")
    public String info(){


        return "ok";
    }

    @RequestMapping("/msg")
    public String msg(String hello){
        hello();
        System.out.printf(hello);


        return "ok";
    }

    @Trace
    public String hello() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "str";
    }

    @RequestMapping("/getInfo")
    public String test(@RequestParam Map param){

        return param.get("foreignKey").toString();
    }

}
