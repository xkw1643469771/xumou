package com.xumou.test.struct.sort;


public class CompSort extends AbstractSort{

    public static void main(String[] args) {
    	new CompSort().start(10000*10);
    }

    public void compSort(int[] is) {
        for (int i = 0; i < is.length - 1; i++) {
            for (int j = i + 1; j < is.length; j++) {
                if(is[i] > is[j])
                	swap(is, i, j);
            }
        }
    }

	@Override
	protected void sort(int[] is) {
		compSort(is);
	}

}