package com.gao.annotaion;

/**
 * User: wangchen
 * Date: 14/12/7
 * Time: 14:11
 */
public class AnnotantedImplements implements AnnotntedInterface {

    @Override
    public void sayhello() {
        System.out.println("hello");
    }
}
