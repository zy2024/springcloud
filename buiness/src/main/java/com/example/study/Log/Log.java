package com.example.study.Log;

import javax.xml.bind.Element;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
String value() default "";
}
