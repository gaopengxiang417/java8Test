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
    }
}
