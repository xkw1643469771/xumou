package com.xumou.test.pattern.builder.food;

/**
 * 食品项
 */
public interface Item {

    Packing packing();
    String name();
    double price();

}