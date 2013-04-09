package com.gao.first;

import java.util.Arrays;
import java.util.List;

/**
 * User: wangchen.gpx
 * Date: 13-4-8
 * Time: 下午4:38
 */
public class LamdbasTest {
    public static void main(String[] args) {
        int z = 2;
        /*(int x , int y ) -  > {
            System.out.println(x + y );
        };*/

       /* int x = 3;
        int y = 5;
        (x , y ) -> x + y;*/

        /*int x = 0;
        x -> {
            System.out.println(x);
        };*/

//        String::valueOf;

        Runnable r = () ->{
            System.out.println("run");
        };

        r.run();

       /* int x = 9;
        int y =5;
        y  -> x +y;*/

        int count = 0;
        List<String> list = Arrays.asList("a", "b", "c");



    }
}
