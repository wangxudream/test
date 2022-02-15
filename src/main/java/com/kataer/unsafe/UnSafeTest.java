package com.kataer.unsafe;

/**
 * @author kataer
 * @description: 通过将类加入根路径获取unsafe
 * @date 2022/2/9
 */
public class UnSafeTest {
  public static final sun.misc.Unsafe UNSAFE;

  static {
    ClassLoader appClassLoader = UnSafeTest.class.getClassLoader();
    System.out.println(appClassLoader.getClass().getName());
    ClassLoader extClassLoader = appClassLoader.getParent();
    System.out.println(extClassLoader.getClass().getName());
    ClassLoader bootstrapClassLoader = extClassLoader.getParent();
    System.out.println(bootstrapClassLoader);
    //sun.misc.Launcher$AppClassLoader
    //sun.misc.Launcher$ExtClassLoader
    //null
    //只有BootstrapClassLoader加载的类才能使用该方法,抛出异常
    UNSAFE = sun.misc.Unsafe.getUnsafe();
  }

  public static void main(String[] args) {

  }
}
