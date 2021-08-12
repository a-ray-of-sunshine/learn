package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

/**
 * 汽水瓶
 */
public class HJ22 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            int total = scan.nextInt();
            int cnt = exFull(total);
            if (cnt > 0) {
                System.out.println(cnt);
            }
        }

        scan.close();
    }

    /**
     * 入参：空瓶子数 返回值：可以喝的数
     */
    private static int exFull(int total) {
        if (total < 2) {
            return 0;
        }
        if (total == 2) {
            return 1;
        }
        if (total == 3) {
            return 1;
        }

        // total > 3
        // pcnt 瓶子数量
        // hcnt 可以喝的数量
        int hcnt = 0, pcnt = 0;
        hcnt = total / 3;
        pcnt = hcnt;
        int a = total - pcnt * 3;
        if (a == 2) { // 2瓶借1还3，喝1
            hcnt += 1;
        } else {
            pcnt += a;
        }

        return hcnt + exFull(pcnt);
    }

}
