package com.gao.lambda;

/**
 * User: wangchen
 * Date: 14-3-23
 * Time: 20:33
 */
public class LocalVariableTest {

    static int num3;
    int num4;

    public static void main(String[] args) {
        final int num = 1;

        //visit local final variable
        Convertor<Integer, String> convertor = (from) -> String.valueOf(from + num);

        //visit local variable,but you can not change it
        //jvm has compiled to final ,so you can not change it
        int num1= 3;
        Convertor<Integer, String> convertor1 = (from) -> {
//          num1 = 3;
            return String.valueOf(from + num1);
        };


        //visit class static variable
        Convertor<Integer, String> convertor2 = (from) -> {
            num3 = 99;
            return String.valueOf(from + num3);
        };

        //visit class instance variable
        Convertor<Integer, String> convertor3 = (from) -> {
//            num4 = 8;
            return String.valueOf("22");
        };


    }
}
