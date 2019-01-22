package com.xumou.test.pattern.builder.food.impl;

import com.xumou.test.pattern.builder.food.Item;
import com.xumou.test.pattern.builder.food.Packing;

/**
 * 汉堡
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

}