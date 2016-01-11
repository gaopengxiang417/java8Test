package com.gao;

/**
 * User: wangchen
 * Date: 15/11/20
 * Time: 14:40
 */
public class PrimeNumberTest {

    public static final int NUM = 10000000;

    public static void main(String[] args) {

        //计算开始时间
        long start = System.currentTimeMillis();

        //计算到的最多的个数
        int max = NUM >> 1;

        //构造标记数组
        boolean[] result = new boolean[max];

        //平方根
        int sqar = (int) Math.sqrt(Integer.valueOf(NUM).doubleValue());

        //初始化为true
        for (int i = 0; i < max; i++) {

            result[i] = true;
        }

        for (int i = 3; i <= sqar; i = i + 2) {

            int tmp = (i - 3) >> 1;
            if (result[tmp]) {

                for (int j = i * i; j < NUM; j = j + (i << 1)) {

                    int inttmp = (j - 3) >> 1;
                    result[inttmp] = false;
                }
            }
        }


        int count = 0;
        for (int i = 0; i < max; i++) {
            if (result[i]) {
                count++;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - start);
        System.out.println(count);
    }
}
