package com.sunshine.learn.thread;

import java.util.ArrayList;
import lombok.SneakyThrows;

public class ArrayListMultiThread {

  @SneakyThrows
  public static void main(String[] args) {

    ArrayList<Integer> data = new ArrayList<>(100);

    Thread t1 = new Thread(new AddThread(data));
    Thread t2 = new Thread(new AddThread(data));

    t1.start();
    t2.start();

    t1.join();
    t2.join();
    System.out.println(data.size());

  }
}

class AddThread implements Runnable {

  private ArrayList<Integer> data;

  public AddThread(ArrayList<Integer> data) {
    this.data = data;
  }

  @Override
  public void run() {

    for (int i = 0; i < 100000; i++) {
      // 不对 data 进行同步，可能导致多线程下访问异常
      // synchronized (data) {
        data.add(i);
      // }
    }

  }
}
