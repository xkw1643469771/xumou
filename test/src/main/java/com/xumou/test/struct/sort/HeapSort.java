package com.xumou.test.struct.sort;


public class HeapSort extends AbstractSort{

    public static void main(String[] args) {
        new HeapSort().start(10000*10000);
    }

    public void heapSort(int[] is){
        for (int j = is.length/2 - 1; j >= 0 ; j--) {
            toDown(is, j, is.length);
        }
        for (int j = is.length - 1; j >= 0; j--) {
            swap(is, 0, j);
            toDown(is, 0, j);
        }
    }

    public void toDown(int[] is, int idx, int len){
        for (int i = idx*2+1; i < len; i = i*2+1) {
            if (i + 1 < len && is[i] < is[i + 1]){
                i = i + 1;
            }
            if(is[idx] < is[i]) {
            	swap(is, idx, i);
                idx = i;
            } else {
                break;
            }
        }
    }

	@Override
	protected void sort(int[] is) {
		heapSort(is);
	}

}