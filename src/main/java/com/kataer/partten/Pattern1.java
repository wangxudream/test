package com.kataer.partten;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/9/2
 */
public class Partten1 {
  private static final Pattern compile = Pattern.compile("< img src=\"*\">");

  public static void main(String[] args) {
    Matcher matcher = compile.matcher("123131223< img src=\"aaa\">321412412432< img src=\"bbb\">");
    if (matcher.find()) {
      String group_0 = matcher.group(0);
      System.out.println(group_0);
      String group_1 = matcher.group(1);
      System.out.println(group_1);
    }
  }
}
