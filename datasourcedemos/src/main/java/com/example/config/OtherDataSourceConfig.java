package com.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = "com.example.othermapper", sqlSessionTemplateRef  = "secondSqlSessionTemplate")
public class OtherDataSourceConfig {

    @Bean("seconddatesource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource createDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean("secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("seconddatesource") DataSource oterdatesource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(oterdatesource);
       // bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:othermapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager secondTransactionManager( @Qualifier("seconddatesource") DataSource otherDataSource) {
        return new DataSourceTransactionManager(otherDataSource);
    }

    @Bean(name = "secondSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory otherSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(otherSqlSessionFactory);
    }

}
