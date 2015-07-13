package com.gao.annotaion;

/**
 * User: wangchen
 * Date: 14/12/7
 * Time: 14:07
 */
public class AnnotantedSubClass extends AnnotantedSuperClass {
    @Override
    public void sayhello() {
        System.out.println("hello");
    }
}
