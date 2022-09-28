package com.kataer.partten;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kataer
 * @description: 正则匹配
 * @date 2022/9/2
 */
public class Pattern2 {
  /**
   * S表示任何非空白字符
   */
  private static final Pattern compile = Pattern.compile("\\s");

  public static void main(String[] args) {
    String str = "\r\naaa";
    Matcher matcher = compile.matcher(str);
    int count = 0;
    while (matcher.find()) {
      count++;
      System.out.println("输出:" + matcher.group(0) + "内容");
    }
    System.out.println(count);
  }
}
