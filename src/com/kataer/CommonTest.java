package com.kataer;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonTest {
  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    list.add(null);
    list.add(null);
    list.add("A");
    HashMap<String, Integer> map = new HashMap<>();
    list.forEach(item -> {
      Integer orDefault = map.getOrDefault(item, 0);
      map.put(item,++orDefault);
    });
    System.out.println(map);
  }

  public static void test(int i, int j) {
    System.out.println(i + ":" + j);
  }
}
