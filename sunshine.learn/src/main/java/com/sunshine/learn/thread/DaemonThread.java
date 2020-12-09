package com.sunshine.learn.thread;

import lombok.SneakyThrows;

public class DaemonThread {

  public static class DaemonT extends Thread {

    @Override
    public void run() {
      while (true) {

        System.out.println("i'm alive");

        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }
  }

  @SneakyThrows
  public static void main(String[] args) {
    Thread t = new DaemonT();
    // daemon: true 主线程退出，此类线程也退出
    t.setDaemon(true);
    t.start();

    Thread.sleep(2000);
    System.out.println("main thread exit");
  }

}
