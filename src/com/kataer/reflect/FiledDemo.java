package com.kataer.reflect;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiledDemo {

  public static void main(String[] args) {
//    List<Integer> integers = new ArrayList<>();
//    integers.add(100);
//    integers.add(1);
//    HashMap<Integer, List<Integer>> map = new HashMap<>();
//    map.put(1, integers);
//    for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
//      List<Integer> value = entry.getValue();
//      value.sort(new Comparator());
//      System.out.println(value);
//    }
//
//    for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
//      List<Integer> value = entry.getValue();
//      System.out.println(value);
//    }
    BigDecimal aTotal = new BigDecimal(5);
    BigDecimal bTotal = new BigDecimal(3);
    BigDecimal divide = aTotal.divide(bTotal, 0, RoundingMode.HALF_UP);
    System.out.println(divide);
  }

  private static class Comparator implements java.util.Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
      return o1 - o2;
    }
  }
}
