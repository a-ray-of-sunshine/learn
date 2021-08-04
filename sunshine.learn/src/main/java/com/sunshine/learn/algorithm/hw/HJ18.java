package com.sunshine.learn.algorithm.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * IP地址识别
 */
public class HJ18 {

    private static int[] IP_OFFSET = { 24, 16, 8, 0 };

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        List<String> ips = new ArrayList<>();
        List<String> masks = new ArrayList<>();
        while (scan.hasNext()) {
            String line = scan.nextLine();
            if (line.trim().length() == 0) {
                break;
            }
            String[] text = line.split("~");
            
            String ip = text[0];
            String mask = text[1];

            System.out.println(validFormat(ip) + " " + validFormat(mask));
            ips.add(ip);
            masks.add(mask);
        }

        List<Integer> lips = new ArrayList<>();
        List<Integer> lmasks = new ArrayList<>();

        // for (String ip : ips) {
        //     Integer iip;
        //     if ((iip = validFormat(ip)) != -1) {
        //         lips.add(iip);
        //     }
        // }

        // for (String mask : masks) {
        //     Integer imask;
        //     if ((imask = validFormat(mask)) != -1) {
        //         lmasks.add(imask);
        //     }
        // }

        System.out.println(lips);
        System.out.println(lmasks);

        scan.close();

    }

    private static long validFormat(String ip) {

        // int result = 0;
        int result[] = {0, 0, 0, 0};

        String[] ip_arr = ip.split("\\.");
        if (ip_arr.length != 4) {
            return -1;
        }

        try {
            for (int i = 0; i < ip_arr.length; i++) {
                String ip_bit = ip_arr[i];
                
                int bits = Integer.parseInt(ip_bit);
                result[i] = bits << IP_OFFSET[i];
            }
        } catch (Exception e) {
            return -1;
        }

        return (long)(result[0] | result[1] | result[2] | result[3]);
    }

}
