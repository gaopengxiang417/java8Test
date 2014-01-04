package com.gao.lambda;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午8:29
 */
public class FunctionInterfaceMain {
    public static void main(String[] args) {
        int inital = 30;

        FunctionInterfaceTest interfaceTest = new FunctionInterfaceTest();

        interfaceTest.hello(inital, s -> s + 2);
        interfaceTest.hello(inital, s -> s -100);
        interfaceTest.hello(inital, s -> "string length".length() + s);
    }
}
