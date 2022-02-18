package com.kataer.cas;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 这是AQS源码里的例子
 * 该版本的锁能被其它线程释放
 */
public class Lock01 {
  private static int m;

  class Sync extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
      //cas成功则认为获取锁成功
      if (compareAndSetState(0, 1)) {
        /**
         * 设置所有者线程
         */
        setExclusiveOwnerThread(Thread.currentThread());
        return true;
      }
      return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
      /**
       * 没有上锁
       */
      if (getState() == 0) throw new IllegalMonitorStateException();
      /**
       * 释放锁
       */
      setExclusiveOwnerThread(null);
      setState(0);
      return true;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread[] threads = new Thread[1000];
    for (int i = 0; i < 1000; i++) {
      threads[i] = new Thread(new Runnable() {
        @Override
        public void run() {
          m++;
        }
      });
    }
    for (Thread thread : threads) {
      thread.start();
    }
    for (Thread thread : threads) {
      thread.join();
    }
    System.out.println(m);
  }
}

