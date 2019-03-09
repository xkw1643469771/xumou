package com.xumou.test.struct.sort;


public class InsertSort extends AbstractSort {

	public static void main(String[] args) {
		new InsertSort().start(10000*10);
	}

	public void insertSort(int[] is) {
		insertSort(is, 0, is.length - 1);
	}
	
	public void insertSort(int[] is, int start, int end){
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

	@Override
	protected void sort(int[] is) {
		insertSort(is);
	}

}