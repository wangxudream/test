package com.kataer.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * 测试线程池
 * todo 完成完整的测试
 *
 * @author kataer
 * @date 2022/1/7
 */
public class TreadPoolTest {
  public static void main(String[] args) throws Exception {
    ExecutorService executor = new ThreadPoolExecutor(
        //核心线程数
        1,
        //最大线程数
        5,
        //非核心线程空闲时间
        60,
        TimeUnit.SECONDS,
        //任务队列 线程安全的容器
        new ArrayBlockingQueue<Runnable>(1),
        //线程factory
        new ThreadFactory() {
          final AtomicInteger count = new AtomicInteger();

          @Override
          public Thread newThread(Runnable r) {
            return new Thread(r, "Test-Thread-" + count.incrementAndGet());
          }
        },
        //拒绝策略
        new ThreadPoolExecutor.CallerRunsPolicy());
    ReentrantLock reentrantLock = new ReentrantLock();
    executor.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("Thread01:" + Thread.currentThread().getName());
        try {
          Thread.sleep(10 * 60 * 1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    //提交第二个任务
    executor.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("Thread02:" + Thread.currentThread().getName());
      }
    });

    //提交第三个任务
    executor.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("Thread03:" + Thread.currentThread().getName());
      }
    });
//    executor.shutdown();
  }
}
