package com.kataer.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
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
        new ArrayBlockingQueue<Runnable>(2));
    System.out.println(threadPoolExecutor.getPoolSize());
//    事先创建所有线程
//    threadPoolExecutor.prestartCoreThread();
//    threadPoolExecutor.prestartAllCoreThreads();
    final CountDownLatch countDownLatch = new CountDownLatch(8);
    for (int i = 0; i < 8; i++) {
      threadPoolExecutor.submit(new Runnable() {
        public void run() {
          for (; ; ) {
            //线程数未达到核心线程数 会创建新的线程来执行这个任务，此时线程数为2
            System.out.println(Thread.currentThread().getName());
            System.out.println(threadPoolExecutor.getCorePoolSize());
            System.out.println(threadPoolExecutor.getPoolSize());
            try {
              Thread.sleep(600000);
              countDownLatch.countDown();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      });
    }
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
