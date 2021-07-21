package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

/**
 * 进制转换
 */
public class HJ5 {

    private static int radix = 16;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            if (line.startsWith("0x")) {
                line = line.substring(2);
            }
            int number = Integer.parseInt(line, radix);
            System.out.println(number);
        }
        scan.close();
    }
}
