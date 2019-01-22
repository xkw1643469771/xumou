package com.xumou.test.pattern.builder.food.impl;

import com.xumou.test.pattern.builder.food.Packing;

/**
 * 盒装
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}