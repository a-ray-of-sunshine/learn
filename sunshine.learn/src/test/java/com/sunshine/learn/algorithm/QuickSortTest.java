package com.sunshine.learn.algorithm;

import org.junit.Test;

import com.sunshine.learn.utils.Utils;

public class QuickSortTest {

	private QuickSort quickSort = new QuickSort();
	
	@Test
	public void testDivide(){
		int[] array = Utils.generateArray(20, 90, 10);
		Utils.printArray(array);
		quickSort.divide(array, 0, array.length - 1);
		Utils.printArray(array);
	}
	
	@Test
	public void testQuickSort(){
		int[] array = Utils.generateArray(20, 90, 10);
		Utils.printArray(array);
		quickSort.quickSort(array, 0, array.length - 1);
		Utils.printArray(array);
	}
}
