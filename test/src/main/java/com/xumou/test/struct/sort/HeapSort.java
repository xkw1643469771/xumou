package com.xumou.test.struct.sort;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] is = new int[32];
        for (int i = 0; i < is.length; i++)
            is[i] = (int)(Math.random()*100);
        heapSort(is);
        System.out.println(Arrays.toString(is));
    }

    public static void heapSort(int[] is){
        for (int j = is.length/2 - 1; j >= 0 ; j--) {
            toDown(is, j, is.length);
        }
        for (int j = is.length - 1; j >= 0; j--) {
            swop(is, 0, j);
            toDown(is, 0, j);
        }
    }

    public static void toDown(int[] is, int idx, int len){
        for (int i = idx*2+1; i < len; i = i*2+1) {
            if (i + 1 < len && is[i] < is[i + 1]){
                i = i + 1;
            }
            if(is[idx] < is[i]) {
                swop(is, idx, i);
                idx = i;
            } else {
                break;
            }
        }
    }

    public static void swop(int[] os, int i1, int i2){
        int temp = os[i1];
        os[i1] = os[i2];
        os[i2] = temp;
    }

}