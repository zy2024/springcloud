package com.example.study.accept;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.tools.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect  // 使用@Aspect注解声明一个切面
@Component
public class AcceptCon {


    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     *
     * execution(public * *(..)) 任意的公共方法
     * execution（* set*（..）） 以set开头的所有的方法
     * execution（* com.LoggerApply.*（..））com.LoggerApply这个类里的所有的方法
     * execution（* com.annotation.*.*（..））com.annotation包下的所有的类的所有的方法
     * execution（* com.annotation..*.*（..））com.annotation包及子包下所有的类的所有的方法
     * execution(* com.annotation..*.*(String,?,Long)) com.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
     * execution(@annotation(com.lingyejun.annotation.Lingyejun))
     */
    @Pointcut("@annotation(com.example.study.log.Log)")
    public void logPointCut() {}

    @Around("logPointCut()")
    public void around(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{

            for (Object o : args) {
                System.out.printf(JSON.toJSONString(o));
            }
        }catch (Exception e){

        }


    }

}
