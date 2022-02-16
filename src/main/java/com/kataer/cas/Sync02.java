package com.kataer.cas;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Sync02 {
  private static int m;

  class Sync extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
      if (compareAndSetState(0, 1)) {
        setExclusiveOwnerThread(Thread.currentThread());
        return true;
      }
      return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
      if (getState() == 0) {
        throw new IllegalStateException("");
      }
      if (getState() == 0) throw new IllegalMonitorStateException();
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

