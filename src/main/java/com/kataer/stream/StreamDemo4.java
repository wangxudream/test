package com.kataer.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamDemo4 {
  public static void main(String[] args) {
    List<String> channelCodeList = new ArrayList<>();
    channelCodeList.add("A");
    channelCodeList.add("B");
    List<String> res = Optional.of(channelCodeList.stream()
        .map(new Function<String, String>() {
          @Override
          public String apply(String s) {
            System.out.println(s);
            return s;
          }
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList())).orElse(Collections.emptyList());

    System.out.println(res);
  }
}
