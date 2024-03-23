package com.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;

@Component
public class InterceptorConfig implements ApplicationRunner {
    @Autowired
    private List<SqlSessionFactory> sqlSessionFactory;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        MyInterceptor myInterceptor = new MyInterceptor();
        Iterator<SqlSessionFactory> iterator = this.sqlSessionFactory.iterator();
        while (iterator.hasNext()){
            SqlSessionFactory sqlSessionFactory1 = (SqlSessionFactory)iterator.next();
            sqlSessionFactory1.getConfiguration().addInterceptor(myInterceptor);
        }
    }
}
