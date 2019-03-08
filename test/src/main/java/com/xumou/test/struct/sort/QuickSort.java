package com.xumou.test.struct.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] is = new int[10000*1000];
        for (int i = 0; i < is.length; i++)
            is[i] = (int)(Math.random()*is.length);

        long s1 = System.currentTimeMillis();
        quickSort(is, 0, is.length - 1);
        long s2 = System.currentTimeMillis();
        System.out.println(s2-s1);

        checkSort(is);
    }


    private static void quickSort(int[] is) {
        quickSort(is, 0, is.length - 1);
    }

    private static void quickSort(int[] is, int i, int j) {
        if(j - i > 10) {
            int idx  = divideArr(is, i, j);
            quickSort(is, i, idx);
            quickSort(is, idx, j);
        }else{
            sort(is, i, j);
        }
    }

    private static void sort(int[] is, int start, int end){
        for (int i = start + 1; i <= end ; i++) {
            for (int j = i; j > start; j--) {
                if(is[j] < is[j-1]){
                    swap(is, j, j-1);
                }else{
                    break;
                }
            }
        }
    }

    private static int divideArr(int[] is, int start, int end) {
        int center = (start + end) / 2;
        if(is[center] < is[start])
            swap(is, center, start);
        if(is[end] < is[center])
            swap(is, end, center);
        if(is[center] < is[start])
            swap(is, center, start);
        swap(is, center, end - 1);
        int tag = is[end - 1];
        int left = start + 1;
        int right = end - 2;
        while(true){
            while(left < right && is[left] < tag)
                left ++;
            while(left < right && is[right] >= tag)
                right --;
            if(left >= right)
                break;
            swap(is, left ++, right --);
        }
        return left;
    }

    private static void swap(int[] is, int i, int j) {
        int temp = is[i];
        is[i] = is[j];
        is[j] = temp;
    }

    private static void checkSort(int[] is) {
        int s = is[0];
        for (int i = 1; i < is.length; i++) {
            if(s > is[i]){
                throw new RuntimeException("不是有序的");
            }
            s = is[i];
        }
    }

}