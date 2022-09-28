package com.kataer;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
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
  private volatile long holdClientIdentity = NO_HOLD;
  private volatile AtomicLong lockCount = new AtomicLong(0);
  private volatile AtomicBoolean locked = new AtomicBoolean(false);
  private final LinkedBlockingDeque<Waiter> blockingDeque = new LinkedBlockingDeque<>(1000);
  private final Thread timerThread = new Thread(new WaitTimeoutRunner(), "timer_thread");
  private final Thread releaseThread = new Thread(new LockTimeoutRunner(), "release_thread");
  private volatile Waiter lockWaiter = null;

  public MyLock() {
    timerThread.start();
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
    Thread thread = Thread.currentThread();
    //锁被持有
    if (locked.get()) {
      if (holdClientIdentity == clientIdentity) {
        //count计数
        long count = lockCount.incrementAndGet();
        log.info("重入锁 clientIdentity :{} count :{}", clientIdentity, count);
        return true;
      }
    }
    //锁未被持有 直接竞争->存入队列
    int count = 1;
    do {
      if (locked.compareAndSet(false, true)) {
        log.info("cas 获取锁 :{}", clientIdentity);
        holdClientIdentity = clientIdentity;
        lockCount.getAndIncrement();
        lockWaiter = new Waiter(clientIdentity, NO_TRY, timeout, thread);
        return true;
      }
      count++;
    } while (count < CAS_TIME);
    blockingDeque.addLast(new Waiter(clientIdentity, NO_TRY, NO_TIMEOUT, Thread.currentThread()));
    log.error("获取锁失败 :{}", clientIdentity);
    return false;
  }


  public boolean tryLock(long clientIdentity, long time) throws InterruptedException {
    long ttl = System.currentTimeMillis() + time;
    //本身已获取锁
    if (holdClientIdentity == clientIdentity) {
      return true;
    }
    //尝试获取锁
    long deadLine = time + System.nanoTime();
    if (doTryLock(deadLine)) {
      return true;
    }
    //

//    //加入队列
//    blockingDeque.addLast(new Waiter(clientIdentity, time, NO_TRY, Thread.currentThread()));
    //自旋等待
    return doTryLock(ttl);
  }

  /**
   * @param deadline
   * @return
   */
  private boolean doTryLock(long deadline) throws InterruptedException {
    while (true) {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      if (deadline > System.nanoTime()) {
        return false;
      }
      if (locked.compareAndSet(false, true)) {
        return true;
      }
    }
  }

  /**
   * @return
   */
  private boolean doTryLockInQueue(long clientIdentity, long time) throws InterruptedException {
    Waiter waiter = new Waiter(clientIdentity, time, NO_TRY, Thread.currentThread());
    blockingDeque.addLast(waiter);
    while (waiter != blockingDeque.getFirst()) {
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
      Thread.sleep(1);
    }
    return false;
  }


  public boolean unlock(long clientIdentity) {
    if (locked.get()) {
      if (holdClientIdentity == clientIdentity) {
        //释放锁
        if (lockCount.decrementAndGet() == 0) {
          locked.set(false);
          holdClientIdentity = NO_HOLD;
          lockWaiter = null;
        }
        return true;
      }
    }
    return false;
  }

  private void releaseLock() {

  }

  private class Waiter {
    private final long identity;
    private final long tryExpireTime;
    private final long lockExpireTime;
    private final Thread thread;

    public Waiter(long identity, long tryExpireTime, long lockExpireTime, Thread thread) {
      this.identity = identity;
      this.tryExpireTime = System.currentTimeMillis() + tryExpireTime;
      this.lockExpireTime = System.currentTimeMillis() + lockExpireTime;
      this.thread = thread;
    }
  }

  private class WaitTimeoutRunner implements Runnable {

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
      while (true) {
        try {
          if (!locked.get()) {
            Waiter waiter = blockingDeque.removeFirst();
            if (waiter.tryExpireTime < System.currentTimeMillis()) {
              //打断线程
              log.info("尝试获取锁失败 打断线程 :{}", waiter.identity);
              waiter.thread.interrupt();
            } else {
              //获取锁
              log.info("队列中获取锁 :{}", waiter.identity);
              locked.getAndSet(true);
              holdClientIdentity = waiter.identity;
              lockCount.getAndIncrement();
            }
          }
          Thread.sleep(1);
        } catch (Exception e) {
          //
        }
      }
    }
  }

  private class LockTimeoutRunner implements Runnable {

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
      while (true) {
        try {
          if (locked.get()) {
            //锁被占用
            if (lockWaiter.lockExpireTime <= System.currentTimeMillis()) {
              //需要释放锁
              log.info("持有锁超时 释放锁 :{}", lockWaiter.identity);
              locked.getAndSet(false);
              lockCount.set(0);
              holdClientIdentity = NO_HOLD;
              lockWaiter = null;
            }
          }
          Thread.sleep(1);
        } catch (Exception e) {
          //
        }
      }
    }
  }
}
