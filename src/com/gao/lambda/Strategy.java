package com.gao.lambda;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午8:51
 * 函数接口
 */
@FunctionalInterface
public interface Strategy {
    /**
     * 执行方法
     */
    void performTask();
}
