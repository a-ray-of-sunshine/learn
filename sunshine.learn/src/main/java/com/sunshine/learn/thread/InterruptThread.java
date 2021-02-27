package com.sunshine.learn.thread;

public class InterruptThread {


  public static void main(String[] args) throws InterruptedException {

    Thread t1 = new Thread(() -> {
      while (true){

        // CASE1: sleep时会被中断
//          try {
//            Thread.sleep(500);
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//            System.out.println(Thread.currentThread().isInterrupted());
//          }

        // CASE2: 通过isInterrupted判断线程是否被中断
        if (Thread.currentThread().isInterrupted()){
          System.out.println("has interrupt exit");
          break;
        }

        System.out.println("i'm live");
        Thread.yield();
      }

    });

    t1.start();
    Thread.sleep(1000);
    //  中断调用并不会终止线程，
    t1.interrupt();
    System.out.println("has call interrupt");

  }

}
