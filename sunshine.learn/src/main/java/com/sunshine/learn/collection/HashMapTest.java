package com.sunshine.learn.collection;

import java.util.HashMap;

public class HashMapTest {

  private static final int MAXIMUM_CAPACITY = 1 << 30;

  public static void main(String[] args) {


    HashMap<String, String> map = new HashMap();

    for (int i = 0; i < 1<<20; i++){
      map.put("key" + i, "value" + i);
    }



    System.out.println(tableSizeFor(0));
    System.out.println(tableSizeFor(1));
    System.out.println(tableSizeFor(5));
    System.out.println(tableSizeFor(9));
    System.out.println(tableSizeFor(17));
    System.out.println(tableSizeFor(64));
    System.out.println(tableSizeFor(1 << 10));

  }

  static int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }
}
