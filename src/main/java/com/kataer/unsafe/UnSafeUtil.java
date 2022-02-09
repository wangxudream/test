package com.kataer.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/9
 */
public class UnSafeUtil {
  public static Unsafe UNSAFE;

  static {
    try {
      //通过反射获取unsafe单例对象
      Class<Unsafe> unsafeClass = Unsafe.class;
      Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      UNSAFE = (Unsafe) theUnsafe.get(null);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
