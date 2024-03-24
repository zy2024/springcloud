package com.example.aspect;

import com.example.config.MyDataSource;
import com.example.util.DataSourceNote;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MyDataSourceAspect {

    @Pointcut("execution(* com.example.mapper.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DataSourceNote dataSourceNote = method.getAnnotation(DataSourceNote.class);
        if (dataSourceNote != null) {
            String value = dataSourceNote.value();
            MyDataSource.setDataSource(value);
        }
        try {
            return joinPoint.proceed();
        } finally {
            //操作完成，移除指定数据源，还原默认数据源
            MyDataSource.removeDataSource();
        }
    }
}
