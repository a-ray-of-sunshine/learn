package com.sunshine.learn.utils;

import java.util.Random;

/**
 * 2015年6月16日21:30:42
 * 工具类
 */
public class Utils {

	/**
	 * 打印数组
	 * @param array
	 */
	public static void printArray(int[] array){
		int size = array.length;
		
		for(int i = 0; i < size; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
		
	}
	
	/**
	 * 生成数组 数值范围 [start, end], 元素个数  count
	 * @param start 数组的数值范围
	 * @param end   数组的数值范围
	 * @param count 数组元素的个数
	 */
	public static int[] generateArray(int start, int end, int count){
		int[] array = new int[count];
		
		Random random = new Random();
		for(int i = 0; i < count; i++){
			array[i] = random.nextInt(end - start + 1) + start;
		}
		
		return array;
	}
}
