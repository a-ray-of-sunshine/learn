package com.sunshine.learn.thread;

import com.sunshine.learn.utils.Utils;

public class VolatileSemantic {

  static volatile int i = 0;
  static volatile int j = 0;

  static void one() {
    i++;
    j++;
  }

  static void two() {
    // System.out.println("i=" + i + " j=" + j);
    if (i < j){
      System.out.println("i=" + i + " j=" + j);
    }
  }

  public static void main(String[] args) {

    Thread one = new Thread(() -> {while(true){one();}});
    Thread two = new Thread(() -> {while(true){two();}});

    one.start();
    two.start();

    Utils.join(one);
    Utils.join(two);

  }
}
