package com.xumou.test.struct.sort;

import java.util.Arrays;

public class CompSort {

    public static void main(String[] args) {
        int[] is = new int[100000];
        for (int i = 0; i < is.length; i++)
            is[i] = (int)(Math.random()*is.length);
        long s1 = System.currentTimeMillis();
        compSort(is);
        long s2 = System.currentTimeMillis();
        System.out.println(s2-s1);
        System.out.println(Arrays.toString(is));
    }

    private static void compSort(int[] is) {
        for (int i = 0; i < is.length - 1; i++) {
            for (int j = i + 1; j < is.length; j++) {
                if(is[i] > is[j])
                    swap(is, i, j);
            }
        }
    }


    private static void swap(int[] is, int i, int j) {
        int temp = is[i];
        is[i] = is[j];
        is[j] = temp;
    }


}