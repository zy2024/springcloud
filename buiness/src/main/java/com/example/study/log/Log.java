package com.example.study.log;

import java.lang.annotation.*;

/**
 * @author 阿星
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
String value() default "";
}
