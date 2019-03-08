package com.xumou.test.struct.sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        int[] is = new int[100000];
        for (int i = 0; i < is.length; i++)
            is[i] = (int)(Math.random()*is.length);
        long s1 = System.currentTimeMillis();
        insertSort(is);
        long s2 = System.currentTimeMillis();
        System.out.println(s2-s1);
        System.out.println(Arrays.toString(is));
    }

    private static void insertSort(int[] is) {
        for (int i = 1; i < is.length; i++) {
            for (int j = i; j > 0; j--) {
                if(is[j] < is[j-1]){
                    swap(is, j, j-1);
                }else{
                    break;
                }
            }
        }
    }


    private static void swap(int[] is, int i, int j) {
        int temp = is[i];
        is[i] = is[j];
        is[j] = temp;
    }


}