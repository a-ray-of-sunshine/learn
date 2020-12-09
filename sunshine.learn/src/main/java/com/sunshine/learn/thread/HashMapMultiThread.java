package com.sunshine.learn.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.SneakyThrows;

public class HashMapMultiThread {


  @SneakyThrows
  public static void main(String[] args) {

    // 多线程环境下向容器中添加元素会有问题
    // 可以使用并发容器
    // Map<String, Integer> map = new HashMap<>();
    Map<String, Integer> map = new ConcurrentHashMap<>();

    Thread t1 = new Thread(new AddMapThread(0, map));
    Thread t2 = new Thread(new AddMapThread(1, map));

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    // 直接使用 HashMap 结果一般小于 100000
    System.out.println(map.size());

  }

}

class AddMapThread implements Runnable {

  private int start;
  private Map<String, Integer> map;

  public AddMapThread(int start, Map<String, Integer> map) {
    this.start = start;
    this.map = map;
  }

  @Override
  public void run() {
    for (int i = start; i < 100000; i += 2) {
      map.put(String.valueOf(i), i);
    }
  }
}