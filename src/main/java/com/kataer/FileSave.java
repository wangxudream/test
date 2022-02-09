package com.kataer;

import java.io.File;
import java.io.IOException;

public class FileSave {
  public static void main(String[] args) throws IOException {
    File file = new File("C:\\Users\\Administrator\\Desktop\\jars\\78e4f2e7a4d91d58f916f71cdd204650.jar");
    System.out.println(file.isDirectory());
    System.out.println(file.mkdir());
  }
}
