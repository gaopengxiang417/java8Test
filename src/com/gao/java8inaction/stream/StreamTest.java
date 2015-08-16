package com.gao.java8inaction.stream;

import com.gao.first.C;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

        //获取单词的长度
        List<String> wordList = Arrays.asList("Java8", "lambda", "In", "Action");
        List<Integer> wordLength = wordList.stream().map(String::length).collect(Collectors.toList());
        System.out.println(wordLength);

        //可以连接两个map
        List<Integer> dishLengthList = menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
        System.out.println(dishLengthList);


        //解决stream合并的问题
        List<String> stringList = wordList.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(stringList);

        //返回一组数据的平方
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squareList = numberList.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(squareList);

        //构造一个数据对
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> collect1 = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        System.out.println(collect1);

        //查询是否有一个元素匹配
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("the menu is vegetarian friendly!!");
        }

        //查询是否全部匹配
        System.out.println(menu.stream().allMatch(d -> d.getCalories() < 1000));

        Optional<Dish> dishOptional = menu.stream().filter(Dish::isVegetarian).findAny();
        System.out.println(dishOptional.get());

        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);


        System.out.println(numberList.stream().map(s -> s * s).filter(s -> s % 3 == 0).findFirst().get());

        //reduce操作
        Integer reduce = numberList.stream().reduce(0, (a, b) -> a + b);
        System.out.println(reduce);

        //更加精简的代码
        System.out.println(numberList.stream().reduce(0, Integer::sum));

        //没有初始值的reduce
        numberList.stream().reduce(Integer::sum).ifPresent(System.out::println);

        numberList.stream().reduce(Integer::min).ifPresent(System.out::println);
        numberList.stream().reduce(Integer::max).ifPresent(System.out::println);

        //计算盘子个数
        System.out.println(menu.stream().map(d -> 1).reduce(0, Integer::sum));


    }
}
