package com.sunshine.learn.algorithm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sunshine.learn.utils.Utils;

public class QuickSortTest {

	private QuickSort quickSort = new QuickSort();
	
	@Test
	public void testDivide(){
		int[] array = {23, 45, 26, 83, 42, 13, 30};
		Utils.PrintArray(array);
		assertEquals("equal", quickSort.divide(array, 0, array.length - 1), 3);
		Utils.PrintArray(array);
	}
	
	@Test
	public void testQuickSort(){
		int[] array = {23, 45, 26, 83, 42, 13, 30};
		Utils.PrintArray(array);
		quickSort.quickSort(array, 0, array.length - 1);
		Utils.PrintArray(array);
	}
}
