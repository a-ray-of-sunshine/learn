package com.sunshine.learn.algorithm.hw;

import java.util.Scanner;
import java.util.Stack;

public class HJ3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            Integer[] numbers = readNumbers(scan);
            calc(numbers);
        }
        scan.close();
    }

    private static Integer[] readNumbers(Scanner scan) {
        int count = scan.nextInt();
        Integer[] numbers = new Integer[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = scan.nextInt();
        }
        return numbers;
    }

    private static void calc(Integer[] numbers) {
        sort(numbers);
        numbers = distinct(numbers);
        printArray(numbers);
    }

    private static void printArray(Integer[] array) {
        int size = array.length;

        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }

    private static Integer[] distinct(Integer[] numbers) {

        Stack<Integer> stack = new Stack<>();

        Integer sp = null;

        int count = 0;
        for (Integer num : numbers) {
            if (sp != null && num.equals(sp)) {
                continue;
            }
            stack.push(num);
            sp = num;
            count++;
        }
        return stack.toArray(new Integer[count]);
    }

    private static Integer[] sort(Integer[] numbers) {

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    Integer temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        return numbers;
    }

}
