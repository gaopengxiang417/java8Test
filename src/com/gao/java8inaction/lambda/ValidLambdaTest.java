package com.gao.java8inaction.lambda;

import java.util.function.IntPredicate;

/**
 * User: wangchen
 * Date: 15/8/2
 * Time: 13:42
 */
public class ValidLambdaTest {


    //主要是测试lambda表达式的五种常见形式

    //第一种,只有一个参数,并且为String类型
    //(String s) -> s.length;

    //第二种,只有一个参数,并且为Apple类型
    //(Apple a) -> a.getWeight() > 140

    //第三,有两个参数,并且body为语句块
/*
    (int x, int y) -> {
        System.out.println("result:");
        System.out.println(x + y);
    }
*/

    //第四种,没有参数,只有一个返回值
    //() -> 43

    //整体lambda表达式的语法为
    //(parameters) -> expression
    //(parameters) -> {statements}

    //() -> {} 这个是合法的lambda表达式,因为他是可以认为没有参数,并且返回值为空
    //() -> "real" 这个也是合法的,他是没有参数,返回一个字符串常量
    //() -> {return "mario"} 这个也是合法的,因为他只是显示的调用了return语句
    //(Integer i) -> return "aas" + i 这个是非法的,显示有return,但是没有用大括号抱起来
    //(String s) -> {"iron man";} 这个是非法的,因为大括号里面是statement而不是expression

    //函数式的接口
    Runnable r = () -> System.out.println("hello");

    //IntPredicate intPredicate = (int anInt) -> anInt % 2 == 0;
    //intPredicate.test(1100);
}
