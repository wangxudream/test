package com.kataer.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kataer
 * @description: TODO
 * @date 2022/1/24
 */
public class ThreadPoolTest3 {
  public static void main(String[] args) {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
        2,
        5,
        60,
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(100));
    System.out.println(threadPoolExecutor.getPoolSize());
    //会创建一个线程
    threadPoolExecutor.prestartCoreThread();
    //线程数为1
    System.out.println(threadPoolExecutor.getPoolSize());
    threadPoolExecutor.submit(new Runnable() {
      public void run() {
        for (; ; ) {
          //线程数未达到核心线程数 会创建新的线程来执行这个任务，此时线程数为2
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
