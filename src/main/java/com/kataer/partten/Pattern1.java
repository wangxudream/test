package com.kataer.partten;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kataer
 * @description: 正则匹配
 * @date 2022/9/2
 */
public class Pattern1 {
  /**
   *S表示任何非空白字符
   */
  private static final Pattern compile = Pattern.compile("< img src=\"\\S+\">");

  public static void main(String[] args) {
    Matcher matcher = compile.matcher("< img src=\"\">123131223< img src=\"aaa\">321412412432< img src=\"bbb\">< img src=");
    while (matcher.find()) {
      System.out.println(matcher.group(0));
    }
  }
}
