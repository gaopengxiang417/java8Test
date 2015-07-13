package com.gao.annotaion;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * User: wangchen
 * Date: 14/12/7
 * Time: 13:55
 */
public class CustomAnnotaionMain {
    public static void main(String[] args) {

        Class<AnnotatedClass> aClass = AnnotatedClass.class;

        //print the class annotaion
        Annotation[] annotations = aClass.getAnnotations();

        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        //whether a annotation present
        if (aClass.isAnnotationPresent(CustomAnnotaionClass.class)) {

            CustomAnnotaionClass annotation = aClass.getAnnotation(CustomAnnotaionClass.class);
            System.out.println(annotation);
        }

        //print method annotation
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {

            if (declaredMethod.isAnnotationPresent(CustomAnnotaionMethod.class)) {

                CustomAnnotaionMethod annotation = declaredMethod.getAnnotation(CustomAnnotaionMethod.class);
                System.out.println(annotation);
            }
        }
    }
}
