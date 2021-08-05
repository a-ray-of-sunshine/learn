package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

/**
 * 简单密码
 */
public class HJ21 {

    private static Range[] ranges = new Range[8];
    static {
        ranges[0] = new Range('a', 'c');
        ranges[1] = new Range('d', 'f');
        ranges[2] = new Range('g', 'i');
        ranges[3] = new Range('j', 'l');
        ranges[4] = new Range('m', 'o');
        ranges[5] = new Range('p', 's');
        ranges[6] = new Range('t', 'v');
        ranges[7] = new Range('w', 'z');
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            String password = scan.nextLine();

            int length = password.length();
            char[] dst = new char[length];
            password.getChars(0, length, dst, 0);

            char[] result = new char[length];
            for (int i = 0; i < length; i++) {
                result[i] = decode(dst[i]);
            }
            System.out.println(new String(result));
        }

        scan.close();

    }

    private static char decode(char c) {

        // 处理小写字母
        if (c >= 'a' && c <= 'z') {
            for (int i = 0; i < ranges.length; i++) {
                Range range = ranges[i];
                if (c >= range.getStart() && c <= range.getEnd()) {
                    return (char) ('0' + 2 + i);
                }
            }
        }

        // 处理大写字母
        if (c >= 'A' && c <= 'Z') {
            // c = c + 'a';
            c += 32;
            if (c == 'z') {
                c = 'a';
            } else {
                c += 1;
            }
        }

        return c;
    }

    static class Range {
        private char start;
        private char end;

        public char getStart() {
            return start;
        }

        public void setStart(char start) {
            this.start = start;
        }

        public char getEnd() {
            return end;
        }

        public void setEnd(char end) {
            this.end = end;
        }

        public Range(char start, char end) {
            this.start = start;
            this.end = end;
        }
    }

}
