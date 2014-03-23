package com.gao.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: wangchen
 * Date: 14-3-23
 * Time: 20:00
 */
public class SortedTest {
    public static void main(String[] args) {

        //before java8
        List<String> list = Arrays.asList("first", "second", "third", "fouth", "fifth");

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(list);

        //first lambda
        List<String> secondList = Arrays.asList("first", "second", "third", "fouth", "fifth");
        Collections.sort(secondList, (String a , String b) -> {
            return a.compareTo(b);
        });

        System.out.println(secondList);

        //second lambda
        List<String> thirdList = Arrays.asList("first", "second", "third", "fouth", "fifth");
        Collections.sort(thirdList, (a , b ) -> a.compareTo(b));
        System.out.println(thirdList);


    }
}
