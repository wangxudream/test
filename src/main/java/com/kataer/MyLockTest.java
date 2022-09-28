package com.kataer;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
  public void test_2() {
    boolean lock1 = myLock.lock(1, 3000);
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
    try {
      boolean lock = myLock.lock(1, 3000);
      log.info("lock :{}", lock);
      lock = myLock.tryLock(2, 1000);
      log.info("lock :{}", lock);
      myLock.unlock(2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
