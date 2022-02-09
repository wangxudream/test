package com.kataer.future;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/9
 */
@Slf4j
public class FutureTest {
  private static long SLEEP_TIME = 3000L;

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<User> future = executorService.submit(new Callable<User>() {
      @Override
      public User call() throws Exception {
        Thread.sleep(SLEEP_TIME);
        log.info("return user");
        return new User();
      }
    });

    try {
      //get方法会阻塞主线程
      log.info("get user");
      log.info("future class :{}", future.getClass().getName());
      User user = future.get(1000L, TimeUnit.MILLISECONDS);
      log.info("user :{}", user);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException | TimeoutException e) {
      e.printStackTrace();
    }
    future.cancel(true);
  }

  @Data
  public static class User {
    private String name;
  }
}
