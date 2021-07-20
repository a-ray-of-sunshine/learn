package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

public class HJ1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);     
        while (in.hasNextLine()) { 
            String a = in.nextLine();
            int ret = a.substring(a.lastIndexOf(" ") + 1).length();
            System.out.println(ret);
        }
        in.close();
    }
}
