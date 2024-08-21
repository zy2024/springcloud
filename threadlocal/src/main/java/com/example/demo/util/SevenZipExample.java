package com.example.demo.util;

import netscape.javascript.JSObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class SevenZipExample {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        // 创建一个CompletableFuture对象，并执行异步任务
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 模拟一个耗时的计算任务
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("111");
            return "111";
        });

        CompletableFuture<String> s2 = CompletableFuture.supplyAsync(() -> {
            // 模拟一个耗时的计算任务
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("我说哈哈哈哈");
            return "result";
        });
        //CompletableFuture.allOf(future,s2).join();
      // String s = future.get(1,TimeUnit.SECONDS);
       // String s1 = s2.get();
        CompletableFuture.allOf(s2,future).join();
        long end = System.currentTimeMillis();

        System.out.printf((end-start)+"时间");

    }
    }

