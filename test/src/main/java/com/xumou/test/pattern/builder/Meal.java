package com.xumou.test.pattern.builder;

import com.xumou.test.pattern.builder.food.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * 套餐
 */
public class Meal {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public double getSumPrice(){
        double sum = 0;
        for (Item item : items) {
            sum += item.price();
        }
        return sum;
    }

    public void showItem(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Item item : items) {
            sb.append("{").append(item.name()).append(",").append(item.packing().pack()).append(",").append(item.price()).append("},");
        }
        sb.append("]");
        System.out.println(sb);
    }

}