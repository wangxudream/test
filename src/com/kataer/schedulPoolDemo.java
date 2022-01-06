package com.kataer;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class schedulPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        ScheduledFuture<?> future = executor.schedule(() -> {
            try {
                System.out.println("before>>>>>");
                Thread.sleep(1000);
                System.out.println("after>>>>>");
            } catch (Exception e) {
                System.out.println("被打断");
            }
        }, 1, TimeUnit.SECONDS);
        Thread.sleep(7000);
        System.out.println("取消任务!");
        boolean cancel = future.cancel(false);
        System.out.println(cancel);
    }
}
