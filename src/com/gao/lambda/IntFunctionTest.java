package com.gao.lambda;

import java.util.function.IntFunction;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午9:40
 */
public class IntFunctionTest {
    public static void main(String[] args) {
        //定义函数
        IntFunction function = x -> x + 2;
        userFunc(function);

        int add = 32;
        IntFunction function1 = x -> x + add;
        userFunc(function1);
        //方法的引用
//        boolean is = String::valueOf(true);
    }

    public static void userFunc(IntFunction<Integer> function) {
        Integer apply = function.apply(2);
        System.out.println(apply);
    }
}
