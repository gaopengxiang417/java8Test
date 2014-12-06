package com.gao.option;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * User: wangchen
 * Date: 14-3-23
 * Time: 20:58
 */
public class PredicateTest {
    public static void main(String[] args) {

        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("china"));

        Predicate<Boolean> predicate1 = Objects::isNull;
        Predicate<Boolean> predicate2 = Objects::nonNull;

        Predicate<String> predicate3 = String::isEmpty;
        Predicate<String> predicate4 = predicate3.negate();

        System.out.println(predicate1.test(true));
        System.out.println(predicate2.test(null));

        System.out.println(predicate3.test("china"));
        System.out.println(predicate4.test(""));
    }
}
