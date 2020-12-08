package com.sunshine.learn.thread;

public class MultiThreadLong {

  public static long t = 0;

  public static class ChangeT implements Runnable {

    private long to;

    public ChangeT(long to) {
      this.to = to;
    }

    public void run() {
      while (true) {
        MultiThreadLong.t = to;
        // System.out.println(MultiThreadLong.t);
        Thread.yield();
      }
    }
  }

  public static class ReadT implements Runnable {

    public void run() {
      while (true) {
        long tmp = MultiThreadLong.t;
        if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L
        && tmp != 222L&& tmp != -777L&& tmp != 555L&& tmp != -666L) {
          System.out.println(tmp);
        }
        Thread.yield();
      }
    }
  }

  public static void main(String[] args) {
    new Thread(new ChangeT(111L)).start();
    new Thread(new ChangeT(-999L)).start();
    new Thread(new ChangeT(333L)).start();
    new Thread(new ChangeT(-444L)).start();
    new Thread(new ChangeT(222L)).start();
    new Thread(new ChangeT(-777L)).start();
    new Thread(new ChangeT(555L)).start();
    new Thread(new ChangeT(-666L)).start();
    new Thread(new ReadT()).start();
  }


}
