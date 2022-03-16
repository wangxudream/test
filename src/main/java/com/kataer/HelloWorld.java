package com.kataer;

import lombok.extern.slf4j.Slf4j;
import sun.security.provider.MD5;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author kataer
 * @description: 测试打包的启动类
 * @date 2022/2/10
 */
@Slf4j
public class HelloWorld {
  public static void main(String[] args) {
    Date date = new Date(System.currentTimeMillis());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String format = simpleDateFormat.format(date);
    System.out.println(format);
  }
}
