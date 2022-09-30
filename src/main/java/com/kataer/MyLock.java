package com.kataer;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/9/28
 * 1、重入
 * 2、超时
 */
@Slf4j
public class MyLock {
  public static final long NO_HOLD = -1;
  public static final long NO_TIMEOUT = -1;
  public static final long NO_TRY = -1;
  public static final int CAS_TIME = 3;
  public static final int BLOCK_TIME = Integer.MAX_VALUE - 1;
  private volatile long holdClientIdentity = NO_HOLD;
  private volatile AtomicLong lockCount = new AtomicLong(0);
  private volatile AtomicBoolean locked = new AtomicBoolean(false);
  private volatile AtomicBoolean cancelLock = new AtomicBoolean(false);
  private volatile AtomicBoolean waiterLock = new AtomicBoolean(false);
  private final LinkedBlockingDeque<Waiter> blockingDeque = new LinkedBlockingDeque<>(1000);
  private final Thread releaseThread = new Thread(new LockTimeoutRunner(), "release_thread");
  private volatile Waiter lockWaiter = null;

  public MyLock() {
    releaseThread.start();
  }

  public void printInfo() {
    log.info("holdClientIdentity :{}", holdClientIdentity);
    log.info("lockCount :{}", lockCount.get());
    log.info("locked :{}", locked.get());
    log.info("lockWaiter :{}", lockWaiter);
  }

  /**
   * 获取锁
   *
   * @param clientIdentity
   * @return
   */
  public boolean lock(long clientIdentity, long timeout) {
    //锁被持有
    if (locked.get()) {
      if (holdClientIdentity == clientIdentity && waiterLock.compareAndSet(false, true)) {
        //count计数
        try {
          long count = lockCount.incrementAndGet();
          log.info("重入锁 clientIdentity :{} count :{}", clientIdentity, count);
        } finally {
          waiterLock.set(false);
        }
        return true;
      }
    }
    //锁未被持有 直接竞争->存入队列
    if (doTryLock(clientIdentity)) {
      setLock(clientIdentity, timeout);
      return true;
    }
    return false;
  }


  public boolean tryLock(long clientIdentity, long time, long timeout) throws InterruptedException {
    //本身已获取锁
    if (holdClientIdentity == clientIdentity) {
      return true;
    }
    //尝试获取锁
    long deadLine = TimeUnit.MILLISECONDS.toNanos(time) + System.nanoTime();
    if (doTryLock(clientIdentity, deadLine)) {
      setLock(clientIdentity, timeout);
      return true;
    }
    if (acquireLockNanos(clientIdentity, deadLine)) {
      setLock(clientIdentity, timeout);
      return true;
    }
    return false;
  }


  private void cancelAcquireLock(Waiter waiter) {
    try {
      while (cancelLock.compareAndSet(false, true)) {
        boolean remove = blockingDeque.remove(waiter);
      }
    } finally {
      cancelLock.set(false);
    }
  }


  private void pork() {
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
    }
  }


  /**
   * @return
   */
  private boolean doTryLock(long clientIdentity) {
    int casCount = 1;
    do {
      if (locked.compareAndSet(false, true)) {
        log.info("cas 获取锁 :{}", clientIdentity);
        return true;
      }
      pork();
    } while (++casCount < CAS_TIME);
    log.error("获取锁失败 :{}", clientIdentity);
    return false;
  }


  /**
   * @param deadline
   * @return
   */
  private boolean doTryLock(long clientIdentity, long deadline) throws InterruptedException {
    int casCount = 1;
    do {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      if (deadline > System.nanoTime()) {
        return false;
      }
      if (locked.compareAndSet(false, true)) {
        return true;
      }
      pork();
    } while (++casCount < CAS_TIME);
    log.error("cas获取锁失败 :{}", clientIdentity);
    return false;
  }

  private boolean acquireLockNanos(long clientIdentity, long deadLine) throws InterruptedException {
    //进入队列
    Waiter waiter = new Waiter(clientIdentity, NO_TIMEOUT, Thread.currentThread());
    try {
      //加至队列
      blockingDeque.addLast(waiter);
      log.info("进入队列后  :{}", clientIdentity);
      //判断队列的头是不是自己
      int count = 0;
      do {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        if (deadLine < System.nanoTime()) {
          log.error("queue获取锁超时 :{}", clientIdentity);
          return false;
        }
        if (blockingDeque.getFirst() == waiter && lockWaiter == null) {
          log.info("queue获取锁成功 :{}", clientIdentity);
          return true;
        }
        pork();
      } while (++count < BLOCK_TIME);
    } finally {
      cancelAcquireLock(waiter);
    }
    log.error("queue获取锁失败 :{}", clientIdentity);
    return false;
  }

  /**
   * 设置获取锁后的状态变化
   *
   * @param clientIdentity
   */
  private void setLock(long clientIdentity, long timeout) {
    locked.set(true);
    holdClientIdentity = clientIdentity;
    lockCount.getAndIncrement();
    lockWaiter = new Waiter(clientIdentity, timeout, Thread.currentThread());
  }


  public boolean unlock(long clientIdentity) {
    if (locked.get()) {
      if (holdClientIdentity == clientIdentity) {
        //释放锁
        if (waiterLock.compareAndSet(false, true) && lockCount.decrementAndGet() == 0) {
          try {
            locked.set(false);
            holdClientIdentity = NO_HOLD;
            lockWaiter = null;
            log.info("释放锁 :{}", clientIdentity);
          } finally {
            waiterLock.set(false);
          }
        }
        return true;
      }
    }
    return false;
  }


  private class Waiter {
    private final long identity;
    private final long lockExpireTime;
    private final Thread thread;

    public Waiter(long identity, long lockExpireTime, Thread thread) {
      this.identity = identity;
      this.lockExpireTime = System.currentTimeMillis() + lockExpireTime;
      this.thread = thread;
    }
  }

  private class LockTimeoutRunner implements Runnable {

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
      while (true) {
        try {
          if (locked.get() && lockWaiter != null) {
            //锁被占用
            if (lockWaiter.lockExpireTime <= System.currentTimeMillis() && lockWaiter.lockExpireTime != NO_TIMEOUT && waiterLock.compareAndSet(false, true)) {
              try {
                //需要释放锁
                log.info("持有锁超时 释放锁 :{}", lockWaiter.identity);
                locked.getAndSet(false);
                lockCount.set(0);
                holdClientIdentity = NO_HOLD;
                lockWaiter = null;
              } finally {
                waiterLock.set(false);
              }
            }
          }
          pork();
        } catch (Exception e) {
          e.printStackTrace();
          //
        }
      }
    }
  }
}
