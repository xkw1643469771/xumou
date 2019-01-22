package com.xumou.test.pattern.builder.food.impl;

/**
 * 百事可乐
 */
public class Pepsi extends ColdDrink {
    @Override
    public String name() {
        return "百事可乐";
    }

    @Override
    public double price() {
        return 35;
    }
}