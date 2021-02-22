package com.sunshine.learn.thread.volatilet;

/**
 * 关于指令重排的研究：https://tech.meituan.com/2014/09/23/java-memory-reordering.html
 */
public class VolatileReorder {

  // 局部变量
  private static int x = 0, y = 0;
  // 全局的共享变量
  // 如果给下面的变量加上 volatile 修饰符，则会禁止指令重排，
  // 不会出现 x == 0 && y == 0 的情况
  private static int a = 0, b = 0;

  public static void main(String[] args) throws InterruptedException {
    int i = 0;
//    int x, y;
    for (; ; ) {
      i++;
      x = 0;
      y = 0;
      a = 0;
      b = 0;
      Thread one = new Thread(() -> {
        //由于线程one先启动，下面这句话让它等一等线程two. 读着可根据自己电脑的实际性能适当调整等待时间.
        shortWait(100000);
        a = 1; // volatile write
        x = b; // volatile read
      });

      Thread other = new Thread(() -> {
        b = 1; // volatile write
        y = a; // volatile read
      });

      one.start();
      other.start();
      one.join();
      other.join();

      String result = "第" + i + "次 (" + x + "," + y + ")";
      // 如果线程1或者线程2没有乱序的话逻辑上是不可能出现
      // x == 0 && y == 0 的情况的
      if (x == 0 && y == 0) {
        System.err.println(result);
        break;
      } else {
        System.out.println(result);
      }
    }
  }


  public static void shortWait(long interval) {
    long start = System.nanoTime();
    long end;
    do {
      end = System.nanoTime();
    } while (start + interval >= end);
  }

}
