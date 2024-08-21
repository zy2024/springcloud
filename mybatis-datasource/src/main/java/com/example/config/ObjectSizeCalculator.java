package com.example.config;

import java.lang.instrument.Instrumentation;

public class ObjectSizeCalculator {
    private static Instrumentation instrumentation;

    public static void premain(String agentArgs, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object obj) {
        if (instrumentation == null) {
            throw new IllegalStateException("Instrumentation environment not initialized.");
        }
        return instrumentation.getObjectSize(obj);
    }

    public static void main(String[] args) {
        // 计算对象的大小
        Object obj = new Object();
        long size = ObjectSizeCalculator.getObjectSize(obj);
        System.out.println("Object size: " + size + " bytes");
    }
}
