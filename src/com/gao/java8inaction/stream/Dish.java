package com.gao.java8inaction.stream;

import java.io.Serializable;

/**
 * User: wangchen
 * Date: 15/8/8
 * Time: 13:27
 */
public class Dish implements Serializable {

    private static final long serialVersionUID = -1535385588757589659L;

    private final String name;

    private final boolean vegetarian;

    private final int calories;

    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                '}';
    }

    public enum Type{
        MEAT,FISH,OTHER;
    }
}
