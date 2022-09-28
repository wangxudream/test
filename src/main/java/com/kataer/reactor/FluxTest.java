package com.kataer.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/6/13
 */
public class FluxTest {
  public static void main(String[] args) {
    Flux.interval(Duration.ofSeconds(4), Duration.ofSeconds(2))
        .doOnNext(i -> {
          System.out.println("thread:" + Thread.currentThread().getName());
          System.out.println("doOnNext " + i);
        }).doOnComplete(() -> {
          System.out.println("doOnComplete " + new Date());
        }).subscribe((i) -> {
          System.out.println("subscribe " + i + ", date: " + new Date());
        });
    try {
      Thread.sleep(1000000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
