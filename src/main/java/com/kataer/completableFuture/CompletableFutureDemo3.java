package com.kataer.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/1/26
 */
public class CompletableFutureDemo3 {
  public static void main(String[] args) {
    CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
      @Override
      public String get() {
        System.out.println(1 / 0);
        return "test";
      }
    }).whenComplete(new BiConsumer<String, Throwable>() {
      @Override
      public void accept(String s, Throwable throwable) {
        System.out.println(s);
      }
    });

    try {
      Thread.sleep(1000);
      String s = future.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
