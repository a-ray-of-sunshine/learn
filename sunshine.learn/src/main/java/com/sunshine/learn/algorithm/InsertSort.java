package com.sunshine.learn.algorithm;

import com.sunshine.learn.utils.Utils;

/**
 * 2015年6月17日11:00:04
 * 插入排序
 */
public class InsertSort {

	public void insertSort(int[] array){
		int size = array.length;
		int i = 1;

		while(i < size){
			int j = i - 1;
			int key = array[i];
			while(j >= 0){
				if(key < array[j]){
					Utils.exchangeElement(array, j, j+1);
					key = array[j];
				}
				j--;
			}
			i++;
		}
	}
}
