package com.sunshine.learn.algorithm.hw;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 合并表记录
 */
public class HJ8 {

    public static void main(String[] args) {

        Map<Integer, Integer> table = new TreeMap<>();

        Scanner scan = new Scanner(System.in);
        int rowNum = scan.nextInt();

        for (int i = 0; i < rowNum; i++) {
            int key = scan.nextInt();
            int value = scan.nextInt();

            table.put(key, table.getOrDefault(key, 0) + value);
        }

        table.forEach((k, v) -> System.out.println(k + " " + v));

        scan.close();
    }

}
