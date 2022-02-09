package com.kataer.unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author kataer
 * @description: 通过反射获取Unsafe
 * @date 2022/2/9
 */
@Slf4j
public class UnSafeTest2 {
  private long age;
  private long size;
  private int state;
  private int high;

  public static void main(String[] args) {
  }

  static {
    try {
      //通过反射获取unsafe单例对象
      Class<Unsafe> unsafeClass = Unsafe.class;
      Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
      //获取属性相对于该对象的偏移量
      long offset1 = UNSAFE.objectFieldOffset(UnSafeTest2.class.getDeclaredField("state"));
      System.out.println("state:" + offset1);
      long offset2 = UNSAFE.objectFieldOffset(UnSafeTest2.class.getDeclaredField("size"));
      System.out.println("size:" + offset2);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
