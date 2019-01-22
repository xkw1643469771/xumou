package com.xumou.test.pattern.builder.food.impl;

/**
 * 鸡肉汉堡
 */
public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "鸡肉汉堡";
    }

    @Override
    public double price() {
        return 50.0;
    }
}