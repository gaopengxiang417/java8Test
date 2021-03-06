package com.gao.java8inaction.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        //partition分组
        Map<Boolean, List<Dish>> collect11 = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(collect11);

        //首先切片,然后分组
        Map<Boolean, Map<Dish.Type, List<Dish>>> collect12 = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        System.out.println(collect12);

        //获取每个切片的最大值的对象
        Map<Boolean, Dish> collect13 = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
        System.out.println(collect13);

        //切片,首先按照是否是蔬菜,然后按照卡路里大于500来区分
        Map<Boolean, Map<Boolean, List<Dish>>> collect14 = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.partitioningBy(dish -> dish.getCalories() > 500)));
        System.out.println(collect14);

        Map<Boolean, Long> collect15 = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.counting()));
        System.out.println(collect15);


        //将数据进行奇数和偶数的分离,并且产出具体的数据列表
        Map<Boolean, List<Integer>> collect16 = IntStream.range(2, 50).boxed().collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println(collect16);
        System.out.println("==========华丽的分割线===========");

        //下面开始所有的Collectors的静态工厂方法

        List<Dish> collect17 = menu.stream().collect(Collectors.toList());
        System.out.println(collect17);

        Set<Dish> collect18 = menu.stream().collect(Collectors.toSet());
        System.out.println(collect18);

        ArrayList<Dish> collect19 = menu.stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect19);

        Long collect20 = menu.stream().collect(Collectors.counting());
        System.out.println(collect20);

        Integer collect21 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(collect21);

        Double collect22 = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(collect22);

        IntSummaryStatistics collect23 = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(collect23);

        String collect24 = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(collect24);

        Optional<Dish> collect25 = menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
        System.out.println(collect25.get());

        Integer collect26 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(collect26);

        Integer collect27 = menu.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        System.out.println(collect27);


        //调用自定义的collector
        Map<Boolean, List<Integer>> collect28 = IntStream.rangeClosed(2, 60).boxed().collect(new PrimeNumbersCollector());
        System.out.println(collect28);

    }

    static class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap(){
                {
                    put(true, new ArrayList<>());
                    put(false, new ArrayList<>());
                }
            };
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (Map<Boolean, List<Integer>> accumulator, Integer candidate) -> {
                accumulator.get(true).add(candidate);
            };
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {

            return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {

                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));

                return map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {

            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }

    public enum CaloricLevel{DIET, NORMAL, FAT}
}
