package com.gao.lambda;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午5:31
 */
@FunctionalInterface
public interface SimpleFunInterface {
    void dowork();

    //可以有多个默认方法
    default void dosomething(){
        System.out.println("do some thing..");
    }
    //函数接口只能有一个方法
//    void test();
}
