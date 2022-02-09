package com.kataer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class KpCode {
  //T1003(1003,xxxx)
  public static void main(String[] args) throws Exception {
    ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
    System.out.println(map.get(null));
//    File file = new File("D:\\开平返回吗.txt");
//    BufferedReader bfReader = new BufferedReader(new FileReader(file));
//    String line = null;
//    while ((line = bfReader.readLine()) != null) {
//      String[] split = line.split("\t");
//      System.out.println("T" + split[0] + "(" + split[0] + ",\"" + split[1] + "\"),");
//    }
  }
}
