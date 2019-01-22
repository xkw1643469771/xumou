package com.xumou.test.pattern.builder;

import org.junit.Test;

/**
 * 测试
 */
public class FootApp {

    @Test
    public void test(){
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.vegMeal();
        System.out.println(vegMeal.getSumPrice());
        vegMeal.showItem();

        Meal nonVegMeal = mealBuilder.nonVegMeal();
        System.out.println(nonVegMeal.getSumPrice());
        nonVegMeal.showItem();

    }

}