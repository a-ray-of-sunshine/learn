package com.sunshine.learn.algorithm;

/**
 * 2015年6月16日20:34:46
 * 快速排序
 */
public class QuickSort {

	public void quickSort(int[] array, int q, int r){
		if(q < r){
			int pivotIndex = this.divide(array, q, r);
			quickSort(array, q, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, r);
		}
	}
	
	/**
	 * 对数组 array 进行划分
	 * @param array
	 * @param q 起始索引
	 * @param r 终止索引
	 * @return
	 */
	public int divide(int[] array, int q, int r){
		int minIndex = q - 1;
		
		// 取最后一个元素为主元
		int pivot = array[r];
		
		for(int maxIndex = q; maxIndex < r; maxIndex++){
			if(array[maxIndex] <= pivot){
				minIndex++;
				exchangeElement(array, minIndex, maxIndex);
			}
		}
		
		exchangeElement(array, ++minIndex, r);
		
		return minIndex;
	}

	private void exchangeElement(int[] array, int minIndex, int maxIndex){
		int temp = 0;
		temp = array[minIndex];
		array[minIndex] = array[maxIndex];
		array[maxIndex] = temp;
	}
}
