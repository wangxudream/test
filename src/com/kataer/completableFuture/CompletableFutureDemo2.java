package com.kataer.completableFuture;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CompletableFutureDemo2 {
  public static void main(String[] args) {
    CompletableFuture<Boolean> future1 = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
      @Override
      public Boolean get() {
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(">>>>>>>>>1");
        return true;
      }
    });


    CompletableFuture<Boolean> future2 = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
      @Override
      public Boolean get() {
        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(">>>>>>>>>2");
        System.out.println(1 / 0);
        return false;
      }
    });
    ArrayList<CompletableFuture> futures = new ArrayList<>();
    futures.add(future1);
    futures.add(future2);
    CompletableFuture<Void> future = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
    try {
      future.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
