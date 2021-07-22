package com.sunshine.learn.algorithm.hw;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 取近似值
 */
public class HJ7 {

    private static final int SCALE = 2;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        double num = scan.nextDouble();
        BigDecimal decimal = new BigDecimal(new Double(num).toString());
        decimal = decimal.setScale(SCALE, BigDecimal.ROUND_HALF_UP);

        System.out.println(decimal.floatValue());
        System.out.println(decimal.intValue());

        scan.close();

    }
}
