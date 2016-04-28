package com.gao;

import java.math.BigDecimal;
import java.util.*;

/**
 * User: wangchen
 * Date: 15/11/27
 * Time: 18:57
 */
public class Test {
    public static void main(String[] args) {

        Integer[] arr = new Integer[10000000];
        long sta1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            arr[i] = i;
        }
        long sta2 = System.currentTimeMillis();
        System.out.println(sta2 - sta1);
        int resou = arr[1000000];
        long sta3 = System.currentTimeMillis();
        System.out.println(sta3 - sta2);

        /*HashSet hashSet = new HashSet();

        hashSet.add("111");
        hashSet.add("222");
        hashSet.add("333");
        hashSet.add("444");
        hashSet.add("555");

        System.out.println(hashSet);

        //filter
        filter(hashSet);

        System.out.println(hashSet);*/
    }

    public static void filter(Collection collection) throws IllegalAccessException, InstantiationException {

        Iterator iterator1 = null;

        Class<? extends Collection> aClass = collection.getClass();

        Collection collection1 = aClass.newInstance();
        collection1.addAll(collection);

        collection.clear();

        for (iterator1 = collection1.iterator(); iterator1.hasNext(); ) {

            Object next = iterator1.next();

            if (next.getClass().equals(String.class)) {

                next = next + "dd";
            }

            collection.add(next);
        }

        System.out.println(collection);

    }
}
