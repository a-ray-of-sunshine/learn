package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

public class HJ2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String text = scan.nextLine().toLowerCase();
        String target = scan.nextLine().toLowerCase();

        char[] chars = new char[text.length()];
        text.getChars(0, text.length(), chars, 0);

        char[] tchar = new char[1];
        target.getChars(0, 1, tchar, 0);

        int count = 0;
        char targetChar = tchar[0];
        for (char c : chars) {
            if (c == targetChar) {
                count++;
            }
        }
        System.out.println(count);
        scan.close();
    }
}
