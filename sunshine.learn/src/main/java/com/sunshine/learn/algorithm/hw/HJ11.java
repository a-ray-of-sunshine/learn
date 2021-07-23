package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

/**
 * 数字颠倒
 */
public class HJ11 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        long num = scan.nextLong();

        char ch;
        while (num != 0) {
            ch = (char) (num % 10 + '0');
            System.out.print(ch);
            num /= 10;
        }

        scan.close();

    }
}
