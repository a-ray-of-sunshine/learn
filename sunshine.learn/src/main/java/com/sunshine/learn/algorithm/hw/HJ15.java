package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

/**
 * 求int型正整数在内存中存储时1的个数
 * 
 * 关键点：位运算
 */
public class HJ15 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int count = 0;
        while (n > 0) {
            if ((n & 1) > 0) {
                count++;
            }
            n = n >> 1;
        }
        System.out.println(count);
        scan.close();
    }
}
