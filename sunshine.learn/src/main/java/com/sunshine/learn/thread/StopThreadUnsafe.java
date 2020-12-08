package com.sunshine.learn.thread;

import lombok.Data;

public class StopThreadUnsafe {

  public static User u = new User();

  public static class ChangeObjectThread extends Thread {

    private volatile boolean stopMe = false;

    public void stopMe() {
      this.stopMe = true;
    }

    @Override
    public void run() {

      while (true) {

        //
        if (this.stopMe) {
          break;
        }

        synchronized (u) {

          int v = (int) (System.currentTimeMillis() / 1000);
          u.setId(v);

          try {
            Thread.sleep(100);
          } catch (Exception e) {
            e.printStackTrace();
          }
          u.setName(String.valueOf(v));
        }

      }

    }
  }

  public static class ReadObjectThread extends Thread {

    @Override
    public void run() {

      while (true) {
        synchronized (u) {
          if (u.getId() != Integer.parseInt(u.getName())) {
            System.out.println(u.toString());
          } else {
            System.out.println("equal...");
          }
        }
        Thread.yield();
      }

    }

  }

  public static void main(String[] args) throws InterruptedException {
    new ReadObjectThread().start();

    while (true) {
      ChangeObjectThread changeObjectThread = new ChangeObjectThread();
      changeObjectThread.start();
      Thread.sleep(200);

      // 通过标记位停止
      // changeObjectThread.stopMe();

      // 使用Stop 直接终止线程运行，内部数据可能存在不一致
       changeObjectThread.stop();
    }

  }

}

@Data
class User {

  private int id;
  private String name;

  public User() {
    this.id = 0;
    this.name = "0";
  }

}