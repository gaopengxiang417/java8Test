package com.gao.annotaion;

/**
 * User: wangchen
 * Date: 14/12/6
 * Time: 15:58
 */
public class MainClass {
    public static void main(String[] args) {

        CanBeRepeated[] repeateds = RepeatedInterface.class.getAnnotationsByType(CanBeRepeated.class);
        for (CanBeRepeated repeated : repeateds) {
            System.out.println(repeated.value());
        }


        //function interface
        MyFunctionInterface myFunctionInterface = (x) -> (x * 20);

        System.out.println(myFunctionInterface.dosomething(33));
    }
}
