package com.kataer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PhonePattern {
  private static final Pattern pattern = Pattern.compile("^1[3456789]\\d{9}$");
//  private static final Pattern pattern = Pattern.compile("^1[3456789][0-9]{9}$");
  public static void main(String[] args) {
    String pattern = PhonePattern.pattern.pattern();
    System.out.println(pattern);
    Matcher matcher = PhonePattern.pattern.matcher("13758274507");
    System.out.println(matcher.matches());

    assert  false;
  }
}
