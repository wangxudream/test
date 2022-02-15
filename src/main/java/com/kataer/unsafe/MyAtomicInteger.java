package com.kataer.unsafe;

import sun.misc.Unsafe;

import java.util.concurrent.*;

public class MyAtomicInteger {
  private static final Unsafe unsafe = UnSafeUtil.UNSAFE;
  //todo 为啥加final会报错误
  private static long valueOffset;
  private int value;

  static {
    try {
      valueOffset = unsafe.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public MyAtomicInteger() {

  }

  public MyAtomicInteger(int innitValue) {
    this.value = innitValue;
  }

  public int getValue() {
    return value;
  }

  public int getAndSet(int value) {
    return unsafe.getAndSetInt(this, valueOffset, value);
  }

  public boolean compareAndSet(int expect, int update) {
    return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
  }

  public int getAndIncrement() {
    return unsafe.getAndAddInt(this, valueOffset, 1);
  }

  public int incrementAndGet() {
    return unsafe.getAndAddInt(this, valueOffset, 1) - 1;
  }

  public int getIntVolatile() {
    return unsafe.getIntVolatile(this, valueOffset);
  }

  public static void main(String[] args) {
    int size = 100;
    final CountDownLatch countDownLatch = new CountDownLatch(size);
    final MyAtomicInteger atomic = new MyAtomicInteger(0);
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    for (int i = 0; i < size; i++) {
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          atomic.getAndIncrement();
          countDownLatch.countDown();
        }
      });
    }

    try {
      countDownLatch.await();
      System.out.println(atomic.getIntVolatile());
      executorService.shutdown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
