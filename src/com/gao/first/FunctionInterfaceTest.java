package com.gao.first;

/**
 * User: wangchen.gpx
 * Date: 13-4-8
 * Time: 下午1:44
 * 函数接口一般不可以覆盖object类中的方法
 */
@FunctionalInterface
public interface FunctionInterfaceTest {
    void run();
    default String toString() {
        return "this is function interface";
    }
}
