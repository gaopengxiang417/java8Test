package com.gao.lambda;

/**
 * User: wangchen
 * Date: 14-3-23
 * Time: 20:14
 */
@FunctionalInterface
public interface Convertor<F, V> {

    V convert(F f);
}
