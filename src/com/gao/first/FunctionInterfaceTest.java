package com.gao.first;

/**
 * User: wangchen.gpx
 * Date: 13-4-8
 * Time: 下午1:44
 * 函数接口一般不可以覆盖object类中的方法,主要的考虑是:
 * 一般是Object对象中的方法一般会有子类进行覆盖，不需要进行接口中进行重写
 */
@FunctionalInterface
public interface FunctionInterfaceTest {
    void run();
    default String toString() {
        return "this is function interface";
    }
}
