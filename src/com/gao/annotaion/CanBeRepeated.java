package com.gao.annotaion;

import java.lang.annotation.*;

/**
 * User: wangchen
 * Date: 14/12/6
 * Time: 15:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Repeatable(RepeatedVlues.class)
public @interface CanBeRepeated {

    String value();
}
