package com.gao.common;

/**
 * User: wangchen
 * Date: 15/11/26
 * Time: 12:57
 */
public class NumberTest {
    public static void main(String[] args) {

        int i = 0;
        int result = 0;
        for (int j = 1; j < 1000000; j++) {

            if (j % 2 == 0 || j % 3 == 0) {
                i++;

                if (i == 2333) {
                    result = j;
                }
            }
        }

        System.out.println(result);
    }
}
