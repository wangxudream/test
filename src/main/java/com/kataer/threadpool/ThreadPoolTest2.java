package com.kataer.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kataer
 * @description: TODO
 * @date 2022/1/24
 */
public class ThreadPoolTest2 {
  public static void main(String[] args) {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
        0,
        2,
        60,
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(100));
    threadPoolExecutor.submit(new Runnable() {
      public void run() {
        for (; ; ) {
          System.out.println(Thread.currentThread().getName());
          System.out.println(threadPoolExecutor.getCorePoolSize());
          System.out.println(threadPoolExecutor.getPoolSize());
          try {
            Thread.sleep(20000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    threadPoolExecutor.submit(new Runnable() {
      public void run() {
        for (; ; ) {
          System.out.println(Thread.currentThread().getName());
          System.out.println(threadPoolExecutor.getCorePoolSize());
          System.out.println(threadPoolExecutor.getPoolSize());
          try {
            Thread.sleep(20000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

  }

}
