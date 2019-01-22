package com.xumou.test.pattern.builder.food.impl;

import com.xumou.test.pattern.builder.food.Packing;

/**
 * 瓶装
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "bottle";
    }
}