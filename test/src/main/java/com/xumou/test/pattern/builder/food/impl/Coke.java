package com.xumou.test.pattern.builder.food.impl;

/**
 * 可口可乐
 */
public class Coke extends ColdDrink{
    @Override
    public String name() {
        return "可口可乐";
    }

    @Override
    public double price() {
        return 30;
    }
}