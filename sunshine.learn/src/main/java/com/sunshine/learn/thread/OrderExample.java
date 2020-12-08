package com.sunshine.learn.thread;

public class OrderExample {


  static int a = 0;
  static boolean flag = false;

  public static class WriteT implements Runnable{

    @Override
    public void run() {
      a = 1;
      flag = true;
    }
  }

  public static class ReadT implements Runnable{

    @Override
    public void run() {
      if (flag){
        System.out.println(a + 1);
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread writeT = new Thread(new WriteT());
    Thread readT = new Thread(new ReadT());
    readT.start();
    writeT.start();

    writeT.join();
    readT.join();

  }

}
