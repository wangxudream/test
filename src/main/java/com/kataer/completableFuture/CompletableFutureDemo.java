package com.kataer.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CompletableFutureDemo {
  public static void main(String[] args) {
    whenComplete();
    System.out.println(">>>>>>>>>>>>>>>");
    handle();
  }

  public static void whenComplete() {
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
      @Override
      public Integer get() {
        try {
          System.out.println("start task>>>>");
          printThreadName();
          Thread.sleep(5000);
          System.out.println("finish task>>>>");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return 500;
      }
    }).whenComplete((integer, throwable) -> {
      try {
        System.out.println("start complete>>>>");
        printThreadName();
        Thread.sleep(5000);
        System.out.println("res:" + integer);
        System.out.println(throwable);
        System.out.println("finish complete>>>>");
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    try {
      System.out.println("wait res >>>>");
      printThreadName();
      Integer res = future.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    printThreadName();
  }

  public static void handle() {
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
      @Override
      public Integer get() {
        System.out.println("start task");
        printThreadName();
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println("task_1_res:" + i);
        return i;
      }
    }).handle((integer, throwable) -> {
      System.out.println("handle");
      printThreadName();
      Random random = new Random();
      int j = random.nextInt(10);
      System.out.println("task_2_res:" + j);
      return j + integer;
    });

    try {
      Integer sum = future.get();
      System.out.println(sum);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  public static void printThreadName() {
    System.out.println("Thread_Name:" + Thread.currentThread().getName());
  }
}
