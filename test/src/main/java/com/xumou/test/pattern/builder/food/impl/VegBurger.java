package com.xumou.test.pattern.builder.food.impl;

/**
 * 蔬菜汉堡
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "蔬菜汉堡";
    }

    @Override
    public double price() {
        return 25.0;
    }
}