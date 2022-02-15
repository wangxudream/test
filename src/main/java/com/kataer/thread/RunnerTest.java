package com.kataer.thread;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/11
 */
public class RunnerTest {
  public static void main(String[] args) {
    Runnable runnable = ()-> System.out.println("aaaa");
    runnable.run();
  }
}
