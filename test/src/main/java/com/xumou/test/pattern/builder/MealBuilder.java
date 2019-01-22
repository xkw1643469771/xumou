package com.xumou.test.pattern.builder;

import com.xumou.test.pattern.builder.food.impl.ChickenBurger;
import com.xumou.test.pattern.builder.food.impl.Coke;
import com.xumou.test.pattern.builder.food.impl.Pepsi;
import com.xumou.test.pattern.builder.food.impl.VegBurger;

/**
 * 套餐组装
 */
public class MealBuilder {

    public Meal vegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal nonVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

}