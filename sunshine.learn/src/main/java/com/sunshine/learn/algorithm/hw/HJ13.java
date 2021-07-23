package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

public class HJ13 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();

        String[] words = line.split("\\s+");
        for (int i = words.length; i > 0; i--) {
            System.out.print(words[i - 1] + " ");
        }

        scan.close();
    }

}
