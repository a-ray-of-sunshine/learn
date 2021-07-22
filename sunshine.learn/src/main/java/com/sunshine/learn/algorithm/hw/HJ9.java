package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

/**
 * 提取不重复的整数
 */
public class HJ9 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String num = scan.nextLine();
        int length = num.length();

        byte[] bytes = num.getBytes();

        byte[] result = new byte[length];

        int result_index = 1;
        result[0] = bytes[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            byte b = bytes[i];
            if (!contain(bytes, b, i + 1)) {
                result[result_index++] = b;
            }
        }

        String string = new String(result, 0, result_index);
        System.out.println(string);

    }

    private static boolean contain(byte[] bytes, byte b, int i) {
        for (int index = i; index < bytes.length; index++) {
            if (bytes[index] == b) {
                return true;
            }
        }
        return false;
    }
}
