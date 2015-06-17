package com.sunshine.learn.algorithm;

import com.sunshine.learn.utils.Utils;

/**
 * 2015年6月17日11:00:04
 * 插入排序
 */
public class InsertSort {

	public void insertSort(int[] array){
		int size = array.length;
		int i = 1, j, key;

		// 对于排序一开始，认为 数组从 [0, j] 是已经排好序的
		while(i < size){
			j = i - 1;
			key = array[i]; // 将索引为 i(即j+1)的元素插入到,已经排好序的数组[0, j]中
			while(j >= 0 && key < array[j]){
				Utils.exchangeElement(array, j, j+1);
				j--;
			}
			i++;
		}
	}
}
