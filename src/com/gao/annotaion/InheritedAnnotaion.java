package com.gao.annotaion;

import java.lang.annotation.*;

/**
 * User: wangchen
 * Date: 14/12/7
 * Time: 14:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface InheritedAnnotaion {
}
