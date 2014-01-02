package com.gao.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * User: wangchen.gpx
 * Date: 13-12-30
 * Time: 下午9:51
 */
public class LambdaTest {
    public static void main(String[] args) throws Exception {
        Callable<Runnable> callable = () -> () -> {
            System.out.println("runnable");
        };

        callable.call();

        Callable<Integer> c = true ? (() -> 23) : (() -> 43);
        c.call();

        Object obj = (Runnable) () -> {
            System.out.println("obj");
        };

        System.out.println(obj.getClass());

        ArrayList<String> list = new ArrayList<>();
        list.add("first");
        List temp = list;
        Object[] a = new Object[2];
//        Arrays.sort(a, Integer::compare);

        list.forEach(System.out::print);

//        List list1 = ArrayList::new;
    }
}
