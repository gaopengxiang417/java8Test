package com.gao.annotaion;

/**
 * User: wangchen
 * Date: 14/12/7
 * Time: 14:08
 */
public class InheritedMain {
    public static void main(String[] args) {

        System.out.println(AnnotantedSuperClass.class.isAnnotationPresent(InheritedAnnotaion.class));
        System.out.println(AnnotantedSubClass.class.isAnnotationPresent(InheritedAnnotaion.class));

        System.out.println(AnnotntedInterface.class.isAnnotationPresent(InheritedAnnotaion.class));
        System.out.println(AnnotantedImplements.class.isAnnotationPresent(InheritedAnnotaion.class));
    }
}
