package com.gao.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * User: wangchen.gpx
 * Date: 14-1-4
 * Time: 下午2:46
 */
public class RosterTest {
    public static void main(String[] args) {

        ArrayList<Person> list = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setBirthday((int) (10  + Math.random()* 20));
            person.setEmailAddress("email:test.@taobao.com" + i);
            person.setGender(random.nextBoolean() ? Person.Sex.FEMALE : Person.Sex.MALE);
            person.setName("person" + i);

            list.add(person);
        }
        //调用方式一，直接用匿名内部类
        printPerson(list, new CheckPersonEligibleForSelectService());
        System.out.println("******华丽的分割线************");
        //调用方式二，自己内部内部实现,有点类似与lambda表达式
        printPerson(list, new CheckPersonEligibleForSelectService(){
            @Override
            public boolean test(Person person) {
                return person.getGender() == Person.Sex.FEMALE &&
                        person.getBirthday() >= 18 &&
                        person.getBirthday() <= 50;
            }
        });

        System.out.println("*********华丽的分割线*******");
        //调用方式三，lambda表达式的使用
        printPerson(list,
                (Person p) -> p.getGender() == Person.Sex.FEMALE &&
                    p.getBirthday() >= 18 &&
                    p.getBirthday() <= 50);

        System.out.println("**********华丽的分割线************");

        //使用JDK自带的predicate来进行调用
        printPersonWitchPredicate(list , p -> p.getGender() == Person.Sex.FEMALE &&
                p.getBirthday() >= 18 &&
                p.getBirthday() <= 60);

        System.out.println("********华丽的分割线*******");

        //调用JDK自带的多个lambda表达式进行调用
        printWithFunction(list, p -> p.getGender() == Person.Sex.FEMALE &&
            p.getBirthday() >= 18 &&
            p.getBirthday() <= 50, p -> p.getEmailAddress(), s -> System.out.println(s));
    }


    /**
     * 判断是否一些人的年龄大于指定的年纪
     * @param roster
     * @param age
     */
    public static void printPersonOlderThan(List<Person> roster, int age) {
        for (Person person : roster) {
            if (person.getBirthday() > age)
                person.pringPerson();
        }
    }

    /**
     * 判断是否一些人的年龄在指定的区间内
     * @param roster
     * @param min
     * @param max
     */
    public static void printPersonRangeAge(List<Person> roster, int min, int max) {
        for (Person person : roster) {
            if (person.getBirthday() > min && person.getBirthday() < max)
                person.pringPerson();
        }
    }

    /**
     * 检测一些人是否在一定的标准内
     * @param roster
     * @param checkPerson
     */
    public static void printPerson(List<Person> roster, CheckPerson checkPerson) {
        for (Person person : roster) {
            if (checkPerson.test(person))
                person.pringPerson();
        }
    }

    /**
     * 调用JDK自带的支持lambda表达式的函数来进行调用
     * @param roster
     * @param tester
     */
    public static void printPersonWitchPredicate(List<Person> roster, Predicate<Person> tester) {
        for (Person person : roster) {
            if (tester.test(person))
                person.pringPerson();
        }
    }

    /**
     * 调用JDK自带的多个lambda表达式来进行方法的调用
     * @param list
     * @param tester
     * @param function
     * @param consumer
     */
    public static void printWithFunction(List<Person> list, Predicate<Person> tester,
                                         Function<Person, String> function, Consumer<String> consumer) {
        for (Person person : list) {
            if (tester.test(person)) {
                String apply = function.apply(person);
                consumer.accept(apply);
            }
        }
    }

    /**
     * 用JDK自带的多个lambda表达式来进行调用，同时采用泛型支持多种表达式
     * @param list
     * @param tester
     * @param function
     * @param consumer
     * @param <X>
     * @param <Y>
     */
    public static <X, Y> void printElement(List<X> list, Predicate<X> tester, Function<X, Y> function, Consumer<Y> consumer) {
        for (X x : list) {
            if (tester.test(x)) {
                Y apply = function.apply(x);
                consumer.accept(apply);
            }
        }
    }

    /**
     * 使用集合自带的stream的方式来进行计算
     * @param list
     */
    public static void printStream(List<Person> list) {
        list.stream().
                filter(p -> p.getGender() == Person.Sex.FEMALE &&p.getBirthday() >= 18 &&p.getBirthday() <= 60)
                .map(p -> p.getEmailAddress())
                .forEach(s -> System.out.println(s));

    }
}
