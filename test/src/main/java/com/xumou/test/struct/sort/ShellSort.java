package com.xumou.test.struct.sort;


public class ShellSort extends AbstractSort {

	public static void main(String[] args) {
		new ShellSort().start(10000*10000);
	}

	@Override
	protected void sort(int[] is) {
		shellSort(is);
	}

	public void shellSort(int[] is) {
		shellSort(is, 0, is.length - 1);
	}

	public void shellSort(int[] is, int startIdx, int endIdx) {
		int len = 1;
		while (len < is.length) {
			len = len * 3 + 1;
		}
		while (len >= 1) {
			len = (len - 1) / 3;
			shellSort(is, len, startIdx, endIdx);
		}
	}

	public void shellSort(int[] is, int len, int startIdx, int endIdx) {
		for (int i = startIdx; i <= endIdx - len; i += 1) {
			for (int j = i + len; j >= len; j -= len) {
				if (is[j] < is[j - len]) {
					swap(is, j, j - len);
				} else {
					break;
				}
			}
		}
	}

}
