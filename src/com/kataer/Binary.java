package com.kataer;

import java.util.Arrays;
import java.util.List;

public class Binary {
  public static void main(String[] args) {
    System.out.println(1 & 1);
    List<Integer> data = Arrays.asList(1, 1, 0, 1, 1, 1);
    int n = 1;
    int count = 0;
    for (int i = 1; i < data.size(); i++) {
      n = n << 1;
      if (data.get(i) == 1) {
        n = n | 1;
      }
    }
    while ((n & 1) != 1) {
      n = n >> 1;
      count++;
    }
//    while (n != 0) {
//      n = n & (n - 1);
//      count++;
//
//    }


    System.out.println(count);
  }
}
