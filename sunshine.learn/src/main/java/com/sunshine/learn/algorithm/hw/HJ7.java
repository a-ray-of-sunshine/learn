package com.sunshine.learn.algorithm.hw;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 取近似值
 */
public class HJ7 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        double num = scan.nextDouble();
        BigDecimal decimal = new BigDecimal(new Double(num).toString());
        int intValue = decimal.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        System.out.println(intValue);

        scan.close();

    }
}
