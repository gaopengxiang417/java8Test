package com.gao;

/**
 * User: wangchen
 * Date: 14-7-28
 * Time: 10:45
 */
public class NullTest {
    public static void main(String[] args) {
        String s = "";
        String ss = null;
        s += ss;
        System.out.println(s);

        double a = 40.8F;
        int b = 100;
        System.out.println(b * a);
    }
}
