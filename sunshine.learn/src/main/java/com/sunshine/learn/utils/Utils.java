package com.sunshine.learn.utils;

/**
 * 2015年6月16日21:30:42
 * 工具类
 */
public class Utils {

	/**
	 * 打印数组
	 * @param array
	 */
	public static void PrintArray(int[] array){
		int size = array.length;
		
		for(int i = 0; i < size; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
		
	}
}
