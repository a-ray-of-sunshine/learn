package com.sunshine.learn.thread;

import com.sunshine.learn.utils.Utils;
import lombok.Data;

public class VolatileCase2 {

  public static void main(String[] args) {
    Example example = new Example();
    Thread thread = new Thread(example);
    thread.start();

    Utils.sleep(1000);
    example.setStop(true);

    Utils.join(thread);
  }
}


@Data
class Example implements Runnable {

  // 不加volatile，程序死循环
  // 加上 volatile 保证可见性
  private boolean stop = false;

  public void run() {
    int i = 0;
    System.out.println("thread1 start loop.");
    while (!isStop()) {
      i++;
      // 以下调用会同步刷新cache.
      // 即使 stop 不是 volatile
      // 依然会因为这类调用更新cache，使得stop可见，然后退出循环
      // Utils.sleep(1);
      // System.out.println(i++);
    }
    System.out.println("thread1 finish loop,i=" + i);

  }

}