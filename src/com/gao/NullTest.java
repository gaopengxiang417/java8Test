package com.gao;

import java.util.HashMap;

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

        StringBuilder sb = new StringBuilder();
        sb.append("23232");
        sb.delete(0, sb.length());

        HashMap<String, String> map = new HashMap<>();
        map.put("roles", sb.toString());
        System.out.println(map.toString());

        Object object = null;
        Long ll = (Long) object;





    }
}
