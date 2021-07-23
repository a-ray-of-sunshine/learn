package com.sunshine.learn.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 字符串排序
 */
public class HJ14 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int count = scan.nextInt();
        List<String> statments = new ArrayList<>(count);
        int index = 0;
        while (index < count) {
            statments.add(scan.next());
            index++;
        }

        statments.sort(String.CASE_INSENSITIVE_ORDER);
        for (String statment : statments) {
            System.out.println(statment);
        }
        scan.close();
    }

}
