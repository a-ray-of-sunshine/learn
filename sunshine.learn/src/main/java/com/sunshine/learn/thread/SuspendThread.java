package com.sunshine.learn.thread;

import lombok.SneakyThrows;

public class SuspendThread {

  @SneakyThrows
  public static void main(String[] args) {

    Object lock = new Object();

    BadSuspend bad1 = new BadSuspend("bad1", lock);
    BadSuspend bad2 = new BadSuspend("bad2", lock);

    bad1.start();
    Thread.sleep(1000);
    // 启动之后马上调用 resume，可能丢失这个 resume消息
    bad2.start();

    bad1.resume();
    bad2.resume();

    bad1.join();
    bad2.join();
  }
}

class BadSuspend extends Thread {

  private Object lock;

  public BadSuspend(String name, Object lock) {
    super(name);
    this.lock = lock;
  }

  @Override
  public void run() {

    synchronized (lock) {
      System.out.println("in " + getName());
      suspend();
    }

  }
}
