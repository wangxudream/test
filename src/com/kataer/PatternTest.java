package com.kataer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
  //("(^|&)" + name + "=([^&]*)(&|$)"
  //(^|&)()(=)([^&]*)(&|$)
  static Pattern pattern = Pattern.compile("([0-9]*)");

  public static void main(String[] args) {
    Matcher matcher = pattern.matcher("aaa1234ccc");
    boolean matches = matcher.matches();
    System.out.println("matches:" + matches);
//    matcher.group("1234");
//    System.out.println("count:" + count);
//    for (int i = 1; i <= count; i++) {
//      System.out.println("group:" + matcher.group(i));
//    }
  }
}
