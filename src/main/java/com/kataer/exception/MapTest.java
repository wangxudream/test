package com.kataer.exception;

import java.io.FileInputStream;
import java.io.IOException;

public class MapTest {
  public static void main(String[] args) {
    MapTest mapTest = new MapTest();
    mapTest.testet();
  }

  public void testet() {
    test();
  }

  public void test() {
    try {
      FileInputStream fileInputStream = new FileInputStream("D:\\test.txt");
      throw new MyException("aaa");
    } catch (RuntimeException | IOException exception) {
      System.out.println("bbbb");
    }
  }

  class MyException extends RuntimeException {
    public MyException() {
    }

    public MyException(String message) {
      super(message);
    }
  }
}
