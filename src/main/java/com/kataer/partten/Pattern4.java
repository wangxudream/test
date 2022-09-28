package com.kataer.partten;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kataer
 * @description: 正则匹配匹配组
 * @date 2022/9/2
 */
public class Pattern4 {
  /**
   *
   */
  private static final Pattern compile = Pattern.compile("(< img src=\"(\\S+)\">)");

  public static void main(String[] args) {
    String str = "< img src=\"123131223\">< img src=\"aaa\">321412412432< img src=\"bbb\">< img src=";
    Matcher matcher = compile.matcher(str);
    while (matcher.find()) {
      System.out.println("匹配字符串:" + matcher.group(0));
      System.out.println("匹配组1:" + matcher.group(1));
      System.out.println("匹配组2:" + matcher.group(2));
    }
  }
}
