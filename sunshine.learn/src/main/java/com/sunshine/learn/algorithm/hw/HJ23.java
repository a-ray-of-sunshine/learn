package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

/**
 * 删除字符串中出现次数最少的字符
 */
public class HJ23 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String line = scan.nextLine();

            // 对每个字符的出现次数打标记
            int[] cnt = new int[26];
            int length = line.length();
            for (int i = 0; i < length; i++) {
                char c = line.charAt(i);
                int index = c - 'a';
                cnt[index] += 1;
            }

            // 计算出最小的出现次数
            int low = Integer.MAX_VALUE;
            for (int i = 0; i < cnt.length; i++){
                if (cnt[i] != 0){
                    low = Math.min(cnt[i], low);
                }
            }

            // 将大于小于出现次数的字符拼接到一起
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                char c = line.charAt(i);
                int index = c - 'a';
                if (cnt[index] > low) {
                    sb.append(c);
                }
            }
            System.out.println(sb.toString());
        }
        scan.close();
    }
}
