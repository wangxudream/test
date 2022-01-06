package com.kataer;

/**
 * @author kataer
 * @date 2021-06-17
 */
public class StringFormat {
  public static void main(String[] args) {
//    String format = String.format("%05d", 1);
//    System.out.println(format);
    String res = null;
    //补充0
    res = String.format("衬衫的价格是 %04d 元", 50);
    System.out.println(res);
    //添加符号
    res = String.format("衬衫的价格是 %+d 元", 50);
    System.out.println(res);
    //补充空格
    res = String.format("衬衫的价格是 % 4d 元", 50);
    System.out.println(res);
    res = String.format("衬衫的价格是 %n %d元", 50);
    System.out.println(res);
  }
}
