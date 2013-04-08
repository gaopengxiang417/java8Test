package com.gao.first;

import java.util.function.Function;

/**
 * User: wangchen.gpx
 * Date: 13-4-2
 * Time: 下午7:17
 */
public class FuncDemo {
    public static void main(String[] args) {
        int incr = 20;
        int mynumber = 15;
        modifyValue(incr , val -> val + incr);


        mynumber = 30;
        modifyValue(incr , val-> val * incr);
        modifyValue(incr , val-> val - 302);
        modifyValue(incr , val-> "somthine".length() + val - 232);
    }
    static void modifyValue(int valueTobeOperate , Function<Integer , Integer> function){
        Integer newValue = function.apply(valueTobeOperate);
        System.out.println(newValue);
    }
}
