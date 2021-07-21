package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

/**
 * 质数因子
 */
public class HJ6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        long num = scan.nextLong();
        long sqnum = (long) Math.sqrt(num);

        for (int i = 2; i <= sqnum; i++) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }

        System.out.println(num == 1 ? "" : num + " ");

        scan.close();
    }
}
