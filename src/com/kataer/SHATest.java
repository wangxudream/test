package com.kataer;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHATest {
  public static void main(String[] args) throws NoSuchAlgorithmException {
    String originalStr = "ABCD";
    MessageDigest sha = MessageDigest.getInstance("SHA-1");
    byte[] digest = sha.digest(originalStr.getBytes());
    System.out.println(DatatypeConverter.printHexBinary(digest).length());
  }
}
