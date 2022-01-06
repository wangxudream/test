package com.kataer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamCurrentTest {
  public static void main(String[] args) {
    String[] strs = new String[1000];
    for (int i = 0; i < 1000; i++) {
      strs[i] = String.valueOf(999 - i);
    }
    List<String> collect = Arrays.stream(strs).parallel().map(item -> {
      return item + "xx";
    }).collect(Collectors.toList());

    System.out.println(Arrays.toString(strs));
  }
}
