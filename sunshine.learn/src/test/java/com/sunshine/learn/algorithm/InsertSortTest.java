package com.sunshine.learn.algorithm;

import org.junit.Test;

import com.sunshine.learn.utils.Utils;

public class InsertSortTest {

	@Test
	public void testInsertSort(){
		int[] array = Utils.generateArray(20, 80, 10);
		Utils.printArray(array);
		InsertSort insertSort = new InsertSort();
		insertSort.insertSort(array);
		Utils.printArray(array);
	}
}
