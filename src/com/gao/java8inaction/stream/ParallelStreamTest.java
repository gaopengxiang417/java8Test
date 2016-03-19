package com.gao.java8inaction.stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * User: wangchen
 * Date: 16/3/10
 * Time: 23:54
 */
public class ParallelStreamTest {

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


        //普通的顺序计算前面的数字之和
        Long reduce = Stream.iterate(1L, i -> i + 1).limit(100).reduce(0L, Long::sum);
        System.out.println(reduce);

        //并行的计算前面数字之和
        Long reduce1 = Stream.iterate(1L, i -> i + 1).limit(100).parallel().reduce(0L, Long::sum);
        System.out.println(reduce1);

        //System.out.println("sequential sum done in :" + measuerSumPerf(Stream, 10_000_000) + "ms");

        //并行的forkjoin框架来计算任务
        long[] longs = LongStream.rangeClosed(1, 1000).toArray();
        NumberSumTask numberSumTask = new NumberSumTask(longs);

        Long invoke = new ForkJoinPool().invoke(numberSumTask);
        System.out.println(invoke);
    }

    static class NumberSumTask extends RecursiveTask<Long> {

        /**
         * 初始的带计算的数据
         */
        private final long[] arrays;

        /**
         * 起始位置
         */
        private final int start;

        /**
         * 结束位置
         */
        private final int end;

        /**
         * 最小的切分快
         */
        public static final long THRESHOLD = 10_000;

        public NumberSumTask(long[] arrays) {
            this.arrays = arrays;
            this.start = 0;
            this.end = arrays.length;
        }

        private NumberSumTask(long[] arrays, int start, int end) {
            this.arrays = arrays;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {

            int lenth = end - start;

            //说明小于指定的个数
            if (lenth <= THRESHOLD) {
                computeSequentially();
            }

            //左边的计算任务
            NumberSumTask leftTask = new NumberSumTask(arrays, start, start + lenth / 2);

            leftTask.fork();

            //右边的计算任务
            NumberSumTask rightTask = new NumberSumTask(arrays, start + lenth / 2, end);

            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();

            return leftResult + rightResult;

        }

        private long computeSequentially() {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += arrays[i];
            }

            return sum;
        }
    }

    static long measuerSumPerf(Function<Long, Long> adder, long n) {

        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {

            long start = System.nanoTime();

            Long sum = adder.apply(n);

            long duration = (System.nanoTime() - start) / 1_000_000;

            System.out.println("result:" + sum);

            if (duration < fastest)
                fastest = duration;
        }

        return fastest;
    }
}
