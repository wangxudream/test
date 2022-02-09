package com.kataer.threadpool;

import java.text.DecimalFormat;

/**
 * @author kataer
 * @description: TODO
 * @date 2022/1/24
 */
public class ThreadPoolTest {
  public static void main(String[] args) {
//    final int COUNT_BITS = Integer.SIZE - 3;
//    System.out.println(Integer.toBinaryString(COUNT_BITS));
//    final int CAPACITY = (1 << COUNT_BITS) - 1;
//    System.out.println(CAPACITY);
    System.out.println(Integer.toBinaryString(127));
    System.out.println(Integer.toBinaryString(-127));
    System.out.println(Integer.toBinaryString(-3));
    System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
    System.out.println(Integer.MAX_VALUE+1);
    System.out.println(Integer.toBinaryString(Integer.MAX_VALUE + 1));
    System.out.println(Integer.toBinaryString(3));
    System.out.println(Integer.toBinaryString(-1));
    parseStatus();
  }

  public static void parseStatus() {
    DecimalFormat df = new DecimalFormat("00000000000000000000000000000000");
    final int COUNT_BITS = Integer.SIZE - 3;
    final int RUNNING = -1 << COUNT_BITS;
    System.out.println("RUNNING:" + RUNNING);
    System.out.println("RUNNING:" + Integer.toBinaryString(RUNNING));
    final int SHUTDOWN = 0 << COUNT_BITS;
    System.out.println("SHUTDOWN:" + SHUTDOWN);
    System.out.println("SHUTDOWN:" + Integer.toBinaryString(SHUTDOWN));
    final int STOP = 1 << COUNT_BITS;
    System.out.println("STOP:" + STOP);
    System.out.println("STOP:" + Integer.toBinaryString(STOP));
    final int TIDYING = 2 << COUNT_BITS;
    System.out.println("TIDYING:" + TIDYING);
    System.out.println("TIDYING:" + Integer.toBinaryString(TIDYING));
    final int TERMINATED = 3 << COUNT_BITS;
    System.out.println("TERMINATED:" + TERMINATED);
    System.out.println("TERMINATED:" + Integer.toBinaryString(TERMINATED));
  }
}
