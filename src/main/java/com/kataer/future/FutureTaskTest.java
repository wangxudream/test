package com.kataer.future;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.util.concurrent.*;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/9
 */
@Slf4j
public class FutureTaskTest {
  private static long SLEEP_TIME = 3000L;
  private static long WAIT_TIME = 4000L;

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.sleep(SLEEP_TIME);
        return "Hello world";
      }
    });
    //execute接口没有返回值
    executorService.execute(futureTask);
    try {
      log.info("cancel");
      boolean cancel = futureTask.cancel(true);
      log.info("cancel :{}", cancel);
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      //get方法会阻塞主线程
      log.info("get res");
      if (!futureTask.isCancelled()) {
        String res = futureTask.get(WAIT_TIME, TimeUnit.MILLISECONDS);
        log.info("res :{}", res);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException | TimeoutException e) {
      e.printStackTrace();
    }

    log.info("end ----->");
  }
}
