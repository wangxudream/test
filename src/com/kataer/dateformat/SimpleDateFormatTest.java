package com.kataer.dateformat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.function.Supplier;

/**
 * @author kataer
 * @date 2022/1/6
 * SimpleDateFormat非线程安全的类
 * 1、创建不同的实例 2、使用锁机制同步 3、使用ThreadLocal为每一个线程创建一个自己的实例
 */
public class SimpleDateFormatTest {

  public static void main(String[] args) {
    try {
      unSafe();
      safe();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void unSafe() throws InterruptedException {
    //核心线程数 最大线程数 空闲时间 空闲时间单位 任务队列 拒绝策略 线程factory
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
    //将hashset设置为线程安全的容器
    Collection<String> dateStrSet = Collections.synchronizedCollection(new HashSet<String>());
    //countdown latch
    CountDownLatch countDownLatch = new CountDownLatch(100);
    //线程共享的format
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 0; i < 100; i++) {
      int finalI = i;
      threadPoolExecutor.execute(new Runnable() {
        @Override
        public void run() {
          Calendar instance = Calendar.getInstance();
          instance.add(Calendar.DATE, finalI);
          String str = format.format(instance.getTime());
          dateStrSet.add(str);
          countDownLatch.countDown();
        }
      });
    }
    countDownLatch.await();
    //数量不是100
    //calendar.setTime(date);
    //calendar 是SimpleDateFormat的成员变量,是临界变量
    System.out.println("size:" + dateStrSet.size());
    System.out.println(dateStrSet);
    //关闭线程池，否则程序无法停止
    threadPoolExecutor.shutdown();
  }

  public static void safe() throws InterruptedException {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100), new ThreadFactory() {
      @Override
      public Thread newThread(Runnable r) {
        return new Thread(r);
      }
    }, new ThreadPoolExecutor.AbortPolicy());
    Collection<String> dateStrSet = Collections.synchronizedCollection(new HashSet<String>());
    CountDownLatch countDownLatch = new CountDownLatch(100);
    for (int i = 0; i < 100; i++) {
      int finalI = i;
      threadPoolExecutor.execute(new Runnable() {
        @Override
        public void run() {
          ThreadLocal<SimpleDateFormat> formatThreadLocal = ThreadLocal.withInitial(new Supplier<SimpleDateFormat>() {
            @Override
            public SimpleDateFormat get() {
              return new SimpleDateFormat("yyyy-MM-dd");
            }
          });
          Calendar instance = Calendar.getInstance();
          instance.add(Calendar.DATE, finalI);
          String str = formatThreadLocal.get().format(instance.getTime());
          System.out.println(str);
          dateStrSet.add(str);
          countDownLatch.countDown();
        }
      });
    }
    countDownLatch.await();
    //数量不是100
    //calendar.setTime(date);
    //calendar 是SimpleDateFormat的成员变量,是临界变量
    System.out.println("size:" + dateStrSet.size());
    System.out.println(dateStrSet);
    //关闭线程池，否则程序无法停止
    threadPoolExecutor.shutdown();
  }
}
