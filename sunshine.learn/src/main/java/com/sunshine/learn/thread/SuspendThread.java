package com.sunshine.learn.thread;

import lombok.SneakyThrows;

public class SuspendThread {

  public static void main(String[] args) {

    // testBad();

    testGood();

  }

  @SneakyThrows
  private static void testGood(){

    Object lock = new Object();

    GoodSuspend good1 = new GoodSuspend("good1", lock);
    GoodSuspend good2 = new GoodSuspend("good2", lock);

    good1.start();
    good2.start();

    Thread.sleep(1000);

    // 挂起线程1
    good1.suspendMe();

    // 10秒之后唤醒线程1, 之后 线程1 参与到后面的调度中
    Thread.sleep(10000);
    good1.resumeMe();

  }


  @SneakyThrows
  private static void testBad(){

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

class GoodSuspend extends Thread {

  private Object lock;
  private volatile boolean supendme = false;

  public GoodSuspend(String name, Object lock) {
    super(name);
    this.lock = lock;
  }

  public void suspendMe() {
    supendme = true;
  }

  public void resumeMe() {
    this.supendme = false;
    synchronized (this) {
      this.notify();
    }
  }

  @SneakyThrows
  @Override
  public void run() {
    while (true){

      synchronized (this){
        while (supendme){
          try {
            System.out.println(getName() + ": i'm suspend");
            wait();
            System.out.println(getName() + ": i'm resume");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }

      synchronized (lock){
        Thread.sleep(2000);
        System.out.println(getName() + ": in GoodSuspend Thread");
      }

      yield();

    }
  }
}
