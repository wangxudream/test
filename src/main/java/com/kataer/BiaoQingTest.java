package com.kataer;

import java.io.UnsupportedEncodingException;

public class BiaoQingTest {
  public static void main(String[] args) throws UnsupportedEncodingException {
    int[] array = {1, 2, 3, 4, 5};
    int cur = 1;
    int pre = 1;
    do {
      pre = cur;
      for (int i = 0; i < array.length; i++) {
        if (array[i] < 5) {
          array[i] = ++array[i];
          cur--;
          if (cur <=0) {
            break;
          }
        }
      }
      print(array);
    } while (cur != pre && cur > 0);
    System.out.println(cur);
  }

  public static void print(int[] array) {
    for (int i : array) {
      System.out.println(i);
    }
    System.out.println(">>>>>>>>>>>>>>>");
  }

  public static void test(int i) {
    System.out.println(i);
  }
}
