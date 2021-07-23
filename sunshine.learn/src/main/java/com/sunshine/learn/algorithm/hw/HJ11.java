package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

/**
 * 数字颠倒
 */
public class HJ11 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String line = scan.next();
        byte[] bytes = line.getBytes();

        int length = bytes.length;
        byte[] res = new byte[length];
        int index = length - 1;
        while (index >= 0) {
            res[index] = bytes[length - index - 1];
            index--;
        }

        String result = new String(res);
        System.out.println(result);

        scan.close();

    }
}
