package com.gao.java8inaction.stream;

/**
 * User: wangchen
 * Date: 15/8/16
 * Time: 10:28
 */
public class Trader {

    private final String name;

    private final String city;


    public Trader(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
