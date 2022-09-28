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
  private static final Pattern compile = Pattern.compile("((\\S+)=(\\S+))*");

  public static void main(String[] args) {
    String str = "q=%e9%ab%98%e5%be%b7%e5%9c%b0%e5%9b%be&qs=HS&pq=%e9%ab%98%e5%be%b7&sc=9-2&cvid=46E2DEAB53AE42D99178384C174311D0&FORM=QBRE&sp=1";
    Matcher matcher = compile.matcher(str);

    while (matcher.find()) {
      System.out.println(matcher.group(1));
      System.out.println(matcher.group(2));
      System.out.println(matcher.group(3));
    }

  }
}
