package com.kataer.future;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author kataer
 * @description: 通过execute方法执行FutureTask
 * @date 2022/2/9
 */
@Slf4j
public class FutureTaskTest2 {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    FutureTask<Void> futureTask1 = new FutureTask<Void>(new Runnable() {
      @Override
      public void run() {
        log.info("run task_1");
      }
    }, null);

    FutureTask<Void> futureTask2 = new FutureTask<Void>(new Runnable() {
      @Override
      public void run() {
        log.info("run task_2");
      }
    }, null);
    executorService.execute(futureTask1);
    executorService.execute(futureTask2);
    try {
      Void res1 = futureTask1.get();
      Void res2 = futureTask2.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
