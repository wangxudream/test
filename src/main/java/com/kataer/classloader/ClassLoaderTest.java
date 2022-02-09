package com.kataer.classloader;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/9
 */
public class ClassLoaderTest {
  public static void main(String[] args) {
    ClassLoader bootLoader = String.class.getClassLoader();
    //bootLoader 为null
    ClassLoader appLoader = ClassLoaderTest.class.getClassLoader();
    ClassLoader extLoader = appLoader.getParent();
    ClassLoader boot = appLoader.getParent();
  }
}
