package com.gao;

import java.lang.reflect.Field;

/**
 * User: wangchen
 * Date: 15/11/9
 * Time: 11:57
 */
public class SeriTest {

    public static void main(String[] args) throws IllegalAccessException {

        Student student = new Student();

        Class<Student> aClass = Student.class;

        Field[] declaredFields = aClass.getDeclaredFields();

        for (Field declaredField : declaredFields) {

            System.out.println(declaredField.getName());

            System.out.println(declaredField.get(student));
        }
    }
}
