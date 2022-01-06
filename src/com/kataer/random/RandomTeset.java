package com.kataer.random;

import java.security.SecureRandom;
import java.util.Random;

public class RandomTeset {
  public static void main(String[] args) {
    randomTest();
  }

  public static void randomTest() {
    Random random1 = new Random(100);
    Random random2 = new Random(100);
    for (int i = 0; i < 100; i++) {
      System.out.println(random1.nextInt());
      System.out.println(random2.nextInt());
      System.out.println(">>>>>>>>>>>>>>>");
    }
  }

  public static void secRandomTest() {
    Integer integer = new Integer(100);
    Random secureRandom1 = new SecureRandom();
    Random secureRandom2 = new SecureRandom();
  }
}
