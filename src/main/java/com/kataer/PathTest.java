package com.kataer;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * nio Path使用
 */
public class PathTest {
  public static void main(String[] args) {
    //拼接路径
    Path path = Paths.get("aaa\\", "\\bbb.txt");
    System.out.println(path.getFileName());
    System.out.println(path.getFileSystem());
    System.out.println(path.getParent());
    System.out.println(path.getNameCount());
    System.out.println(path.getRoot());
  }
}
