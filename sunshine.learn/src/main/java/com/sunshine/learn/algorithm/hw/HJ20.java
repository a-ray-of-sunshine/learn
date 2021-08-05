package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;
import java.util.regex.Pattern;

public class HJ20 {

    private static Pattern[] patterns = new Pattern[4];

    static {
        patterns[0] = Pattern.compile("[A-Z]");
        patterns[1] = Pattern.compile("[a-z]");
        patterns[2] = Pattern.compile("[0-9]");
        patterns[3] = Pattern.compile("[^a-zA-Z0-9]");
    }

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next();
            if (str.length() <= 8) {
                System.out.println("NG");
                continue;
            }

            if (getMatch(str)) {
                System.out.println("NG");
                continue;
            }

            if (getString(str, 0, 3)) {
                System.out.println("NG");
                continue;
            }
            System.out.println("OK");
        }
        sc.close();
    }

    private static boolean getString(String str, int start, int end) {
        if (end >= str.length()) {
            return false;
        }
        if (str.substring(end).contains(str.substring(start, end))) {
            return true;
        } else {
            return getString(str, start + 1, end + 1);
        }
    }

    private static boolean getMatch(String str) {
        int count = 0;

        for (Pattern pattern : patterns) {
            if (pattern.matcher(str).find()) {
                count++;
            }
        }

        if (count >= 3) {
            return false;
        }

        return true;
    }
}
