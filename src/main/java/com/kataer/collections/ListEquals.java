package com.kataer.collections;

import java.util.ArrayList;
import java.util.Objects;

public class ListEquals {
  public static void main(String[] args) {
    ArrayList<String> a = new ArrayList<>();
    a.add("aaa");
    ArrayList<String> b = new ArrayList<>();
    a.add("aaa");
    System.out.println(Objects.equals(a, b));
  }
}
