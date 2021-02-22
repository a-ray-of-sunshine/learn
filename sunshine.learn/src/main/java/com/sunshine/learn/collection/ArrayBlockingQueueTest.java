package com.sunshine.learn.collection;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {

  private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue(3);

  public static void main(String[] args) throws InterruptedException {

    queue.put("hello1");
    queue.put("hello2");
    queue.put("hello3");
    queue.put("hello4");


  }

}
