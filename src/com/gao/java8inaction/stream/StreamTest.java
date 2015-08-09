package com.gao.java8inaction.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * User: wangchen
 * Date: 15/8/8
 * Time: 13:30
 */
public class StreamTest {

    public static void main(String[] args) {

        //菜单列表
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));


        List<String> list = menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getName).limit(3).collect(Collectors.toList());
        System.out.println(list);


        List<String> collect = menu.stream().filter(d -> {
            System.out.println("filtering:" + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            System.out.println("mapping:" + d.getName());
            return d.getName();
        }).limit(3).collect(Collectors.toList());

        System.out.println(collect);

        //stream只能被消耗一次

        //filer with a predicate
        menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());

        //filter with distinct
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

        //truncate a stream
        menu.stream().filter(d -> d.isVegetarian()).limit(3).forEach(System.out::println);

        //skip a stream
        menu.stream().filter(d -> d.isVegetarian()).skip(2).forEach(System.out::println);
    }
}
