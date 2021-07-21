package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;

/**
 * 字符串分隔
 */
public class HJ4 {
    private static final int WORD_SIZE = 8;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line;

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            splitString(line);
        }

        scan.close();
    }

    private static void splitString(String line) {
        if (line == null || line.isEmpty()) {
            return;
        }

        int length = line.length();
        int word_length = (length % WORD_SIZE == 0) ? length / WORD_SIZE : length / WORD_SIZE + 1;

        String[] words = new String[word_length];
        int beginIndex = 0;
        int endIndex = word_length > 1 ? WORD_SIZE : length;
        for (int i = 0; i < words.length; i++) {
            words[i] = line.substring(beginIndex, endIndex);
            beginIndex = endIndex;
            endIndex += WORD_SIZE;
            if (endIndex > length) {
                endIndex = length;
            }
        }

        int last_word_index = word_length - 1;
        String last_word = words[last_word_index];
        int append_size;
        if ((append_size = WORD_SIZE - last_word.length()) != 0) {
            StringBuilder builder = new StringBuilder(last_word);
            for (int i = 0; i < append_size; i++) {
                builder.append("0");
            }
            words[last_word_index] = builder.toString();
        }

        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
    }
}
