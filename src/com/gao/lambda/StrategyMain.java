package com.gao.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午8:52
 */
public class StrategyMain {
    public static void main(String[] args) {
        //不用lambda表达式的时候执行
        List<Strategy> list = new ArrayList<>(Arrays.asList(new LazyStrategy(), new ActiveStrategy()));

        for (Strategy strategy : list) {
            strategy.performTask();
        }

        System.out.println("**********华丽的分割线**********8");
        //如果用lambda表达式执行
        List<Strategy> lambdaList = Arrays.asList(
                () -> {
                    System.out.println("lazy done...");},
                () -> {
                    System.out.println("active done....");
                }
        );


        for (Strategy strategy : lambdaList) {
            strategy.performTask();
        }
    }
}

//延迟执行的方法
class LazyStrategy implements Strategy{
    @Override
    public void performTask() {
        System.out.println("lazy is one ......");
    }
}
//立即执行的方法
class ActiveStrategy implements Strategy{
    @Override
    public void performTask() {
        System.out.println("task is done now...");
    }
}
