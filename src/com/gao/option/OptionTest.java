package com.gao.option;

import java.util.Optional;

/**
 * User: wangchen.gpx
 * Date: 14-1-5
 * Time: 下午8:04
 */
public class OptionTest {
    public static void main(String[] args) {
        //of method
        Optional<String> optional = Optional.of("test");
        System.out.println("ispresent:" + optional.isPresent());

        //of method with a null value
        //this should throw nullpointerException
        try {
            Optional<Object> nullOptional = Optional.of(null);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }

        //ofnullable with a null value
        //this is would return empty optional
        Optional<Object> emptyOptional = Optional.ofNullable(null);
        System.out.println(emptyOptional);

        //isPersent method
        System.out.println(optional.isPresent());
        System.out.println(emptyOptional.isPresent());

        //get method ,if the value is null ,will throw exception
        System.out.println(optional.get());
        try {
            System.out.println(emptyOptional.get());
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }

        //ifPresent if present
        optional.ifPresent(s -> System.out.println("the length of string is " + s.length()));

        //orelse method
        System.out.println(optional.orElse("should not return.."));
        System.out.println(emptyOptional.orElse("from orelse...."));

        //orelseget method
        System.out.println(optional.orElseGet(() -> "should not return"));
        System.out.println(emptyOptional.orElseGet(() -> "from orelseget method"));

        //orelsethrow
        System.out.println(optional.orElseThrow(IllegalArgumentException::new));
        try {
            System.out.println(emptyOptional.orElseThrow(IllegalArgumentException::new));
        } catch (RuntimeException e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }

        //map method
        Optional<String> map = optional.map(value -> value.toUpperCase());
        System.out.println(map.orElse("else option..."));

        //flatmap method
        Optional objectOptional = (Optional) optional.flatMap(value -> Optional.of(value.toLowerCase()));
        System.out.println(objectOptional.orElse("flatmap else"));

        //filter method
        Optional<String> filter = optional.filter(value -> value.length() > 2);
        System.out.println(filter.orElse("filter else method"));
        Optional<Object> filter1 = emptyOptional.filter(v -> v != null);
        System.out.println(filter1.orElse("empty filter"));
    }
}
