package com.xumou.test.struct.sort;

public class QuickSort extends AbstractSort {

	public static void main(String[] args) {
		new QuickSort().start(10000*10000);
	}

	@Override
	protected void sort(int[] is) {
		quickSort(is);
	}
	
	public void quickSort(int[] is) {
		quickSort(is, 0, is.length - 1);
	}

	private void quickSort(int[] is, int i, int j) {
		if (j - i > 10) {
			int idx = divideArr(is, i, j);
			quickSort(is, i, idx);
			quickSort(is, idx, j);
		} else {
			new InsertSort().insertSort(is, i, j);
		}
	}

	private int divideArr(int[] is, int start, int end) {
		int center = (start + end) / 2;
		if (is[center] < is[start])
			swap(is, center, start);
		if (is[end] < is[center])
			swap(is, end, center);
		if (is[center] < is[start])
			swap(is, center, start);
		swap(is, center, end - 1);
		int tag = is[end - 1];
		int left = start + 1;
		int right = end - 2;
		while (true) {
			while (left < right && is[left] < tag)
				left++;
			while (left < right && is[right] >= tag)
				right--;
			if (left >= right)
				break;
			swap(is, left++, right--);
		}
		return left;
	}
	
}