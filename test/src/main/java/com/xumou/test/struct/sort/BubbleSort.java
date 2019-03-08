package com.xumou.test.struct.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] is = new int[100000];
        for (int i = 0; i < is.length; i++)
            is[i] = (int)(Math.random()*is.length);
        long s1 = System.currentTimeMillis();
        bubbleSort(is);
        long s2 = System.currentTimeMillis();
        System.out.println(s2-s1);
        System.out.println(Arrays.toString(is));
    }

    private static void bubbleSort(int[] is) {
        for (int i = 0; i < is.length; i++) {
            for (int j = 0; j < is.length - i - 1; j++) {
                if(is[j] > is[j + 1])
                    swap(is, j, j+1);
            }
        }
    }


    private static void swap(int[] is, int i, int j) {
        int temp = is[i];
        is[i] = is[j];
        is[j] = temp;
    }


}