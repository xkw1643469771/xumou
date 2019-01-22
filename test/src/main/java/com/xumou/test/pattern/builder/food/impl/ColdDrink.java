package com.xumou.test.pattern.builder.food.impl;

import com.xumou.test.pattern.builder.food.Item;
import com.xumou.test.pattern.builder.food.Packing;

/**
 * 冷饮
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }
}