package com.sunshine.learn.utils;

import java.util.Random;

/**
 * 2015��6��16��21:30:42
 * ������
 */
public class Utils {

	/**
	 * ��ӡ����
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
	 * �������� ��ֵ��Χ [start, end], Ԫ�ظ���  count
	 * @param start �������ֵ��Χ
	 * @param end   �������ֵ��Χ
	 * @param count ����Ԫ�صĸ���
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
