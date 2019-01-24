package com.wonders.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解练习
 * @Target 注解的作用范围，
 * @Retention 注解的作用时间
 * @author: zph
 * @data: 2018/12/09 16:29
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddAnnotation {

    int userId() default 0;
    String userName() default "李四";
    String[] arrays() ;

}
