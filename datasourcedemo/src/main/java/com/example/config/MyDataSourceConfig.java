package com.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置类
 */
@Configuration
public class MyDataSourceConfig {
    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置自定义的类DynamicDataSource，这里是把两个（多个）数据源放到DynamicDataSource中，
     * 同时设置一个默认的数据源
     */
    @Bean("dynamicDataSource")
    public MyDataSource dynamicDataSource(@Qualifier("db1") DataSource db1,
                                          @Qualifier("db2") DataSource db2) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("db1", db1);
        targetDataSource.put("db2", db2);
        MyDataSource myDataSource = new MyDataSource();
        myDataSource.setTargetDataSources(targetDataSource);
        myDataSource.setDefaultTargetDataSource(db1);
        return myDataSource;
    }

    /**
     * 把动态数据源放到SqlSessionFactory
     * 同时配置扫描的mapping.xml
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1") DataSource db1,
                                               @Qualifier("db2") DataSource db2) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource(db1, db2));
        //没有xml文件时会报错
      //  sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
