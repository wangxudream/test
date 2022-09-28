package com.kataer.partten;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kataer
 * @description: 正则匹配
 * @date 2022/9/2
 */
public class Pattern3 {
  /**
   * ?存在或不存在
   */
  private static final Pattern compile = Pattern.compile("cars?");

  public static void main(String[] args) {
    String str = "car cars ";
    Matcher matcher = compile.matcher(str);
    while (matcher.find()) {
      System.out.println(matcher.group(0));
    }
  }
}
