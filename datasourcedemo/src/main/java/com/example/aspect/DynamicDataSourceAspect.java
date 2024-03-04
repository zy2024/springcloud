package com.example.aspect;

import com.example.config.DynamicDataSource;
import com.example.util.DynamicDB;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("execution(* com.example.mapper.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-------------> selected dataSource aspect ");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DynamicDB dynamicDB = method.getAnnotation(DynamicDB.class);
        if (dynamicDB != null) {
            String value = dynamicDB.value();
            //切换成指定的数据源
            DynamicDataSource.setDataSource(value);
        }
        try {
            return joinPoint.proceed();
        } finally {
            //操作完成，移除指定数据源，还原默认数据源
            DynamicDataSource.removeDataSource();
        }
    }
}
