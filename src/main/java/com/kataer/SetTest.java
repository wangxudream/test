package com.kataer;

import java.util.*;
import java.util.function.Function;

public class SetTest {
  public static void main(String[] args) {
    Map<String, List<String>> map = new HashMap<>();
    List<String> stringList = Arrays.asList("aaa", "bbb", "ccc");
    List<String> aaa = map.computeIfAbsent("aaa", new Function<String, List<String>>() {
      @Override
      public List<String> apply(String s) {
        return new ArrayList<>();
      }
    });
    aaa.add("aaaa");
    System.out.println(map.get("aaa"));
  }
}
