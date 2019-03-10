package com.xumou.test.struct.sort;

import java.util.Random;

public abstract class AbstractSort {

	private Random random;

	public AbstractSort() {
		random = new Random();
	}

	protected void start(int length) {
		int[] is = arr(length);
		long s1 = System.currentTimeMillis();
		sort(is);
		long s2 = System.currentTimeMillis();
		System.out.println(s2 - s1);
		checkSort(is);
	}

	protected abstract void sort(int[] is);

	private int[] arr(int length) {
		int[] is = new int[length];
		for (int i = 0; i < is.length; i++)
			is[i] = random.nextInt(is.length);
		return is;
	}

	protected void swap(int[] is, int i, int j) {
		int temp = is[i];
		is[i] = is[j];
		is[j] = temp;
	}

	private void checkSort(int[] is) {
		if (is.length == 0) {
			return;
		}
		int s = is[0];
		for (int i = 1; i < is.length; i++) {
			if (s > is[i]) {
				throw new RuntimeException("不是有序的");
			}
			s = is[i];
		}
	}

}
