package com.kataer;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/9/28
 */
@Slf4j
public class MyLockTest {
  private MyLock myLock = null;

  @Before
  public void initLock() throws Exception {
    myLock = new MyLock();
    Thread.sleep(1000);
  }

  @After
  public void printLock() throws Exception {
    myLock.printInfo();
    log.info("================================");
    Thread.sleep(10000);
    myLock.printInfo();
  }

  /**
   * 测试lock
   */
  @Test
  public void test_1() {
    boolean lock = myLock.lock(1, 3000);
    Assert.assertTrue(lock);
  }


  /**
   * 测试重入
   */
  @Test
  public void test_2() throws InterruptedException {
    boolean lock1 = myLock.lock(1, 3000);
    Thread.sleep(10000);
    Assert.assertTrue(lock1);
    boolean lock2 = myLock.lock(1, 3000);
    Assert.assertTrue(lock2);
  }

  /**
   * 测试锁被占用
   */
  @Test
  public void test_3() {
    boolean lock1 = myLock.lock(1, 3000);
    Assert.assertTrue(lock1);
    boolean lock2 = myLock.lock(2, 3000);
    Assert.assertTrue(lock2);
  }

  /**
   * 测试锁被占用重入
   */
  @Test
  public void test_4() {
    boolean lock1 = myLock.lock(1, 3000);
    boolean lock2 = myLock.lock(2, 3000);
    boolean lock3 = myLock.lock(1, 3000);
  }

  /**
   * 测试锁被占用重入
   */
  @Test
  public void test_5() {
    boolean lock1 = myLock.lock(1, 3000);
    boolean lock2 = myLock.lock(2, 3000);
    boolean lock3 = myLock.lock(1, 3000);
  }

  /**
   * 测试unlock
   */
  @Test
  public void test_6() {
    boolean lock1 = myLock.lock(1, 3000);
    myLock.printInfo();
    boolean unlock = myLock.unlock(1);
    myLock.printInfo();
  }

  /**
   * 测试unlock
   */
  @Test
  public void test_7() {
    myLock.lock(1, 3000);
    myLock.unlock(1);
    boolean unlock = myLock.unlock(1);
    log.info("unlock :{}", unlock);
    myLock.printInfo();
  }

  /**
   * 测试unlock
   */
  @Test
  public void test_8() {
    myLock.lock(1, 3000);
    boolean unlock = myLock.unlock(2);
    log.info("unlock :{}", unlock);
  }

  /**
   * 测试tryLock
   */
  @Test
  public void test_9() {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    try {
      new Thread(() -> {
        try {
          myLock.lock(1, 5000);
          countDownLatch.countDown();
          Thread.sleep(1000);
          myLock.unlock(1);
        } catch (Exception e) {
          e.printStackTrace();
        } finally {

        }
      }, "thread_1").start();

      new Thread(() -> {
        try {
          countDownLatch.await();
          myLock.tryLock(2, 1000, 3000);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }, "thread_2").start();

      while (true) {
        Thread.sleep(100);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 测试tryLock
   */
  @Test
  public void test_10() {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    try {
      new Thread(() -> {
        try {
          myLock.lock(1, 5000);
          countDownLatch.countDown();
          myLock.unlock(1);
        } catch (Exception e) {
          e.printStackTrace();
        } finally {

        }
      }, "thread_1").start();

      new Thread(() -> {
        try {
          countDownLatch.await();
          myLock.tryLock(2, 1000, 100000);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }, "thread_2").start();

      while (true) {
        Thread.sleep(100);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * 测试tryLock
   */
  @Test
  public void test_finally() {
    final CountDownLatch countDownLatch = new CountDownLatch(2000);
    final MyLockSub myLockSub = new MyLockSub();
    try {
      new Thread(() -> {
        int count = 0;
        do {
          try {
            boolean lock = myLockSub.lock(1, 100);
            if (lock) {
              myLockSub.unlock(1);
              countDownLatch.countDown();
              count++;
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        } while (count < 2000);
      }, "thread_1").start();

      new Thread(() -> {
        int count = 0;
        do {
          try {
            boolean b = myLockSub.tryLock(2, 1000, 100);
            if (b) {
              myLockSub.unlock(2);
              countDownLatch.countDown();
              count++;
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        } while (count < 1000);
      }, "thread_2");

      countDownLatch.await();
      myLockSub.printRes();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public class MyLockSub extends MyLock {
    private volatile AtomicLong lockCount = new AtomicLong(0);

    @Override
    public boolean lock(long clientIdentity, long timeout) {
      boolean lock = super.lock(clientIdentity, timeout);
      if (lock) {
        lockCount.incrementAndGet();
      }
      return lock;
    }

    @Override
    public boolean tryLock(long clientIdentity, long time, long timeout) throws InterruptedException {
      boolean lock = super.tryLock(clientIdentity, time, timeout);
      if (lock) {
        lockCount.incrementAndGet();
      }
      return lock;
    }

    public void printRes() {
      System.out.println("lockCount :" + lockCount.get());
    }
  }
}
