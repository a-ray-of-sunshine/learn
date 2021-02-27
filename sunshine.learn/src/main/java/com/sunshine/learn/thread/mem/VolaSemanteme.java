package com.sunshine.learn.thread.mem;

public class VolaSemanteme {

    int unvloatileVal = 0;

    volatile boolean flag = false;

    public void init() {
        unvloatileVal = 1;
        flag = true; // 第九行哦
    }

    public void use() {

        if (flag) {
            int LocalA = unvloatileVal;
            if (LocalA == 0) {
                throw new RuntimeException("error");
            }
        }
    }

    public static void main(String[] args) {
        VolaSemanteme volaSemanteme = new VolaSemanteme();

        for (int i = 0; i < 100000; i++) {
            volaSemanteme.init();
            volaSemanteme.use();
        }
    }
}
