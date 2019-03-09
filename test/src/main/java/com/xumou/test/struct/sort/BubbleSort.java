package com.xumou.test.struct.sort;


public class BubbleSort extends AbstractSort{

    public static void main(String[] args) {
    	new BubbleSort().start(10000*10);
    }

    private void bubbleSort(int[] is) {
        for (int i = 0; i < is.length; i++) {
            for (int j = 0; j < is.length - i - 1; j++) {
                if(is[j] > is[j + 1])
                    swap(is, j, j+1);
            }
        }
    }

	@Override
	protected void sort(int[] is) {
		bubbleSort(is);
	}

}