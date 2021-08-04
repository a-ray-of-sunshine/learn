package com.sunshine.learn.algorithm.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 简单记录错误
 */
public class HJ19 {

    private static final int LINE_NUM = 8;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        LogInfo[] result = new LogInfo[LINE_NUM];
        int tail = 0;

        // Deque<String> stack = new ArrayDeque<>();

        List<String> list = new ArrayList<>();
        while (scan.hasNext()) {
            String errorline = scan.nextLine();
            list.add(errorline);
        }

        for (String errorline : list) {

            String[] line = errorline.split(" ");
            Integer lineNum = Integer.parseInt(line[1]);

            String[] tokens = line[0].split("\\\\");
            String fileName = tokens[tokens.length - 1];

            LogInfo log = findLog(result, fileName, lineNum);
            if (log != null) {
                log.setCount(log.getCount() + 1);
            } else {

                log = new LogInfo();
                log.setFileName(fileName);
                log.setLineNum(lineNum);
                log.setCount(1);

                result[tail] = log;
                if (++tail == LINE_NUM) {
                    tail = 0;
                }
            }
        }

        for (int i = tail; i < LINE_NUM; i++) {
            System.out.println(result[i]);
        }
        for (int i = 0; i < tail; i++) {
            System.out.println(result[i]);
        }

        scan.close();
    }

    private static LogInfo findLog(LogInfo[] result, String fileName, Integer lineNum) {

        for (LogInfo logInfo : result) {
            if (logInfo == null) {
                continue;
            }
            if (logInfo.getFileName() == fileName && logInfo.getLineNum() == lineNum) {
                return logInfo;
            }
        }
        return null;
    }

    static class LogInfo {
        private String fileName;
        private int lineNum;
        private int count;

        public int getLineNum() {
            return lineNum;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setLineNum(int lineNum) {
            this.lineNum = lineNum;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return fileName + " " + lineNum + " " + count;
        }
    }

}
