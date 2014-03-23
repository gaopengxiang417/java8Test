package com.gao.first;

/**
 * User: wangchen
 * Date: 14-3-23
 * Time: 18:03
 */
public interface DefaultFormula {

    /**
     * interface method
     * @param x caculate
     * @return
     */
    int calculate(int x);

    /**
     * default method
     * @param x
     * @return
     */
    default double sqart(int x) {
        return Math.sqrt(x);
    }


}
