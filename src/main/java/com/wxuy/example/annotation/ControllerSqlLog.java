package com.wxuy.example.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerSqlLog {

    String value() default "";
}