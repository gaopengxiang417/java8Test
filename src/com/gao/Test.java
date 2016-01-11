package com.gao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: wangchen
 * Date: 15/11/27
 * Time: 18:57
 */
public class Test {
    public static void main(String[] args) {

        HashMap map = new HashMap<>();
        map.put("a", new BigDecimal(232));
        map.put("b", new Long(4));

        HashMap<String, String> result = map;
        HashMap<String, String> stringStringHashMap = new HashMap<>();


        try {

            throw new RuntimeException("test");

        } catch (Exception throwable) {
            System.out.println("....");
            System.out.println(throwable);
        }
    }
}
