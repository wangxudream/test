package com.kataer.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

public class CompletableFutureTest {
  public static void main(String[] args) {
    testSingle();
//    testBatch();
  }

  public static void testSingle() {
    try {
//      ExecutorService executorService = Executors.newFixedThreadPool(2);

      CountDownLatch latch = new CountDownLatch(1);
      System.out.println("latch count:" + latch.getCount());
      MyTask lzq = new MyTask("lzq-", latch);

      CompletableFuture<Void> future = CompletableFuture.runAsync(lzq);

      Void aVoid = future.get();
      System.out.println("get future");
      System.out.println("latch count:" + latch.getCount());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void testBatch() {
    try {
      CountDownLatch latch = new CountDownLatch(10);
      List<CompletableFuture<Void>> futureList = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
        MyTask lzq = new MyTask("lzq-", latch);
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Void>() {
          @Override
          public Void get() {
            lzq.run();
            return null;
          }
        });
        futureList.add(future);
      }

      CompletableFuture<Void> future = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
      future.get();
      latch.await();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static class MyTask implements Runnable {
    private String name;
    private CountDownLatch latch;

    public MyTask(String name, CountDownLatch latch) {
      this.name = name;
      this.latch = latch;
    }

    @Override
    public void run() {
      try {
        System.out.println("start run");
        Thread.sleep(5000);
        //ForkJoinPool.commonPool-worker-9
        String threadName = Thread.currentThread().getName();
        System.out.println(name + " Say Hello " + threadName);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        System.out.println("countDown");
        latch.countDown();
      }
    }

    public String say() {
      try {
        Thread.sleep(3000);
        //ForkJoinPool.commonPool-worker-9
        String threadName = Thread.currentThread().getName();
        System.out.println(name + " Say Hello " + threadName);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return name;
    }
  }
}
