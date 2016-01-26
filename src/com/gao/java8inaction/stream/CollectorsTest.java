package com.gao.java8inaction.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * User: wangchen
 * Date: 16/1/11
 * Time: 22:25
 * 说明:主要是理解collector的使用
 */
public class CollectorsTest {

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


        //使用collect来实现
        Long count = menu.stream().collect(Collectors.counting());
        System.out.println("total count : " + count);

        //使用count来实现
        long count1 = menu.stream().count();
        System.out.println("total count:" + count1);


        //计算最大值和最小值的collect
        //首先定义一个比较器
        Comparator<Dish> comparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> optional = menu.stream().collect(Collectors.maxBy(comparator));
        System.out.println(optional.get());

        //获取最小值
        Optional<Dish> optional1 = menu.stream().collect(Collectors.minBy(comparator));
        System.out.println(optional1.get());


        //汇总方法
        IntSummaryStatistics intSummaryStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(intSummaryStatistics.getSum());

        //平均值
        Double aDouble = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(aDouble);

        //字符串的连接
        String collect = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(collect);

        //reduce的使用
        Integer collect1 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(collect1);

        Optional<Dish> collect2 = menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(collect2.get());

        //几种不同的方式
        String collect3 = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(collect3);

        //Dish dish = menu.stream().collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();

        String collect4 = menu.stream().collect(Collectors.reducing("", Dish::getName, (s1, s2) -> s1 + s2));
        System.out.println(collect4);


        //分组功能
        Map<Dish.Type, List<Dish>> typeListMap = menu.stream().collect(Collectors.groupingBy(Dish::getType));

        System.out.println(typeListMap.values());


        //按照自定义的来进行分组
        Map<CaloricLevel, List<Dish>> collect5 = menu.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));

        System.out.println(collect5.values());

        //多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect6 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        })));

        System.out.println(collect6.values());


        //其他类型的分组和后面的加工
        Map<Dish.Type, Long> collect7 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(collect7);

        //首先按照类型进行分组,然后统计每组的最大的值对应的对象
        Map<Dish.Type, Optional<Dish>> collect8 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(collect8);


        //首先按照类型进行分组,然后另一种方式来统计最大值
        Map<Dish.Type, Dish> collect9 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
        System.out.println(collect9);

        //汇总求和
        Map<Dish.Type, IntSummaryStatistics> collect10 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.summarizingInt(Dish::getCalories)));
        System.out.println(collect10);


    }

    public enum CaloricLevel{DIET, NORMAL, FAT}
}
