package com.sunshine.learn.algorithm;

import org.junit.Test;

public class QuickSortTest {

	private QuickSort quickSort = new QuickSort();
	
	@Test
	public void testDivide(){
		int[] array = {23, 45, 26, 83, 42, 13, 30};
		this.PrintArray(array);
		quickSort.divide(array, 0, array.length - 1);
		this.PrintArray(array);
	}
	
	/**
	 * ´òÓ¡Êý×é
	 * @param array
	 */
	private void PrintArray(int[] array){
		int size = array.length;
		
		for(int i = 0; i < size; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
		
	}
	
	
}
