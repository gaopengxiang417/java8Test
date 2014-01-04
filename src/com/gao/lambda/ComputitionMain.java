package com.gao.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午9:06
 */
public class ComputitionMain {
    public static void main(String[] args) {
        //如果不用lambda表达式的时候写法
        List<Computition<Integer>> list = Arrays.asList(
                new MultiplyCompution(),
                new AddCompution(),
                new SubstractCompution()
        );

        for (Computition<Integer> integerComputition : list) {
            Integer compute = integerComputition.compute(3, 53);
            System.out.println(compute);
        }

        //使用lambda表达式的时候写法
        List<Computition<Integer>> lambdaList = Arrays.asList(
                (a , b) -> a + b,
                (a , b) -> a - b,
                (a , b) -> a * b
        );

        lambdaList.forEach((temp) -> System.out.println(temp.compute(3, 53)));
    }
}

//不同的接口实现

class MultiplyCompution implements Computition<Integer> {
    @Override
    public Integer compute(Integer v1, Integer v2) {
        return v1 * v2;
    }
}
class SubstractCompution implements Computition<Integer> {
    @Override
    public Integer compute(Integer v1, Integer v2) {
        return v1 - v2;
    }
}
class AddCompution implements Computition<Integer>{
    @Override
    public Integer compute(Integer v1, Integer v2) {
        return v1 + v2;
    }
}
