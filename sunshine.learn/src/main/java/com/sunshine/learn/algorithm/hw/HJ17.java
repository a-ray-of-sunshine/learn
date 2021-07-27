package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 坐标移动
 */
public class HJ17 {

    private static final String COORDINATE_REGEX = "([AWSD])(\\d{1,2})";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.next();
        scan.close();

        Pattern pattern = Pattern.compile(COORDINATE_REGEX);

        int X = 0, Y = 0;
        String[] tokens = line.split(";");
        for (String token : tokens) {
            Matcher matcher = pattern.matcher(token);
            if (matcher.matches()) {
                String command = matcher.group(1);
                int offset = Integer.parseInt(matcher.group(2));

                switch (command) {
                    case "A":
                        X -= offset;
                        break;
                    case "D":
                        X += offset;
                        break;
                    case "S":
                        Y -= offset;
                        break;
                    case "W":
                        Y += offset;
                        break;
                }
            }
        }
        System.out.println(X + "," + Y);
    }

}
