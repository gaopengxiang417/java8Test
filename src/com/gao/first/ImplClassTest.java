package com.gao.first;

/**
 * User: wangchen.gpx
 * Date: 13-4-8
 * Time: 下午1:59
 */
public class ImplClassTest implements FunctionInterfaceTest {
    @Override
    public void run() {
        System.out.println("run.....");
    }

    @Override
    public String toString() {
        return "implclass return";
    }

    public static void main(String[] args) {
        ImplClassTest implClassTest = new ImplClassTest();
        System.out.println(implClassTest.toString());
        implClassTest.run();
    }
}
