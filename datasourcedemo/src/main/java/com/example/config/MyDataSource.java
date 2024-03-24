package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 扩展 Spring 的 AbstractRoutingDataSource 抽象类，重写 determineCurrentLookupKey 方法
 * 动态数据源
 * determineCurrentLookupKey() 方法决定使用哪个数据源
 */
@Slf4j
public class MyDataSource extends AbstractRoutingDataSource {
    public static final ThreadLocal<String> holder = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String type) {
        holder.set(type);
    }

    public static String getDataSource(){
        return holder.get();
    }

    public static void removeDataSource(){
        holder.remove();
    }
}
