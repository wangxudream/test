package com.kataer;

import java.io.File;
import java.io.FileInputStream;

public class P12Reader {
  public static void main(String[] args) throws Exception {
    File file = new File("D:\\天空岛\\积分YYp12\\apiclient_cert.p12");
    FileInputStream fileInputStream = new FileInputStream(file);
    int available = fileInputStream.available();
    byte[] bytes = new byte[available];
    int read1 = fileInputStream.read(bytes);
    String s = new String(bytes,"utf8");
    System.out.println(s);
  }
}
