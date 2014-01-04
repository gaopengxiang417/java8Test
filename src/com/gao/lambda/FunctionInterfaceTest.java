package com.gao.lambda;

import java.util.function.Function;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午8:27
 * 自定义的支持lambda表达式的类
 */
public class FunctionInterfaceTest {

    /**
     * 接受函数接口进行lambda的方式处理
     * @param inital
     * @param function
     */
    void hello(Integer inital, Function<Integer, Integer> function){
        Integer apply = function.apply(inital);
        System.out.println(apply);
    }
}
