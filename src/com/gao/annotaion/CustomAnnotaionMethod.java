package com.gao.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: wangchen
 * Date: 14/12/6
 * Time: 16:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomAnnotaionMethod {

    String author() default "wangchen.method";

    String date();

    String description();
}
