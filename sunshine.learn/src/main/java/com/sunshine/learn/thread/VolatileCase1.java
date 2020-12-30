package com.sunshine.learn.thread;

import java.util.concurrent.TimeUnit;

public class VolatileCase1 {

  volatile boolean running = true;

  public void run() {
    while (running) {
      // System.out.println("sfe");
      // System.out.println("i'm living.");
    }
    System.out.println(Thread.currentThread().getName() + " end of execution ");
  }


  public void stop() {
    running = false;
    System.out.println(Thread.currentThread().getName() + " thread Modified running to false");
  }

  public static void main(String[] args) throws Exception {

    VolatileCase1 vc = new VolatileCase1();

    Thread t1 = new Thread(vc::run , "Running-Thread");

    Thread t2 = new Thread(vc::stop , "Stop-Thread");

    t1.start();
    TimeUnit.SECONDS.sleep(1);
    t2.start();

  }

}
