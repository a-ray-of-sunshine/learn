package com.sunshine.learn.algorithm.hw;

import java.util.BitSet;
import java.util.Scanner;

public class HJ10 {

    private static final int NBITS = 128;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = scan.nextLine();
        byte[] bytes = line.getBytes();
        BitSet bits = new BitSet(NBITS);
        bits.clear();

        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            if (0 < b && b < NBITS) {
                int bitIndex = b;
                bits.set(bitIndex);
            }
        }

        int count = 0;
        for (int i = 0; i < bits.length(); i++) {
            if (bits.get(i)) {
                count++;
            }
        }
        System.out.println(count);

        scan.close();
    }
}
