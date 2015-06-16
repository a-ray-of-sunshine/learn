package com.sunshine.learn.algorithm;

/**
 * 2015��6��16��20:34:46
 * ��������
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
	 * ������ array ���л���
	 * @param array
	 * @param q ��ʼ����
	 * @param r ��ֹ����
	 * @return
	 */
	public int divide(int[] array, int q, int r){
		int minIndex = q - 1;
		
		// ȡ���һ��Ԫ��Ϊ��Ԫ
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
