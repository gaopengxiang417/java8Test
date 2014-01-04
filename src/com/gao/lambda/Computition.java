package com.gao.lambda;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午9:05
 * 用来支持lambda表达式的函数接口
 */
public interface Computition<V> {
    /**
     * 用来进行计算两个值的函数接口
     * @param v1
     * @param v2
     * @return
     */
    V compute(V v1, V v2);
}
