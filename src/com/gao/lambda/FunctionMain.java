package com.gao.lambda;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午5:50
 */
public class FunctionMain {
    public static void main(String[] args) {

        carray(new SimpleFunInterface() {
            @Override
            public void dowork() {
                System.out.println("first do work");
            }
        });

        carray(() -> System.out.println("do work....."));
    }

    public static void carray(SimpleFunInterface simpleFunInterface) {
        simpleFunInterface.dowork();
    }
}


