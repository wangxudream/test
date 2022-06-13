package com.kataer.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 排他锁的简单实现
 */
@Slf4j
public class Lock02 implements Lock {
  /**
   * 只是用来测试结果的变量
   */
  private static volatile int m;

  private Sync sync;

  public Lock02() {
    this.sync = new Sync();
  }

  @Override
  public void lock() {
    sync.acquire(1);
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {

  }

  @Override
  public boolean tryLock() {
    return false;
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    return false;
  }

  @Override
  public void unlock() {
    sync.release(1);
  }

  @Override
  public Condition newCondition() {
    return null;
  }

  /**
   * 这是一个排他锁的实现
   */
  private static class Sync extends AbstractQueuedSynchronizer {
    public Sync() {
      super();
    }

    /**
     * 重现tryAcquire方法
     *
     * @param arg
     * @return
     */
    @Override
    protected boolean tryAcquire(int arg) {
      //cas成功则认为获取锁成功
      if (compareAndSetState(0, 1)) {
        /**
         * 设置所有者线程
         */
        setExclusiveOwnerThread(Thread.currentThread());
        /**
         * 将该处设置未false可以debug整体获取锁流程
         */
        return true;
      }
      return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
      if (getExclusiveOwnerThread() != Thread.currentThread()) {
        throw new IllegalMonitorStateException("not lock");
      }

      /**
       * 释放锁
       */
      setExclusiveOwnerThread(null);
      setState(0);
      return true;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    debug();
  }

  /**
   * 单线程测试AQS流程
   */
  public static void debug() {
    Lock02 lock = new Lock02();
    lock.lock();
    try {

    } catch (Exception e) {

    } finally {
      lock.unlock();
    }
  }

  public static void useSync() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(100);
    CountDownLatch countDownLatch = new CountDownLatch(1000);
    Lock02 lock = new Lock02();
    for (int i = 0; i < 1000; i++) {
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          //调用acquire
//          sync.acquire(1);
//          try {
//            m++;
//            countDownLatch.countDown();
//          } catch (Exception e) {
//            log.error(e.getMessage());
//          } finally {
//            sync.release(1);
//          }
          lock.lock();
          try {
            m++;
            countDownLatch.countDown();
          } catch (Exception e) {

          } finally {
            lock.unlock();
          }

        }
      });
    }
    countDownLatch.await();
    System.out.println(m);
    executorService.shutdown();
  }
}

