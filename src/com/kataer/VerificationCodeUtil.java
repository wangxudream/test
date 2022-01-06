package com.kataer;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

public class VerificationCodeUtil {
  private static final int CODE_LENGTH = 6;
  private static final String SYMBOLS = "0123456789";
  private static final Random RANDOM = new SecureRandom();

  public static String generateVerificationCode() {
    char[] code = new char[CODE_LENGTH];
    for (int i = 0; i < CODE_LENGTH; i++) {
      code[i] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
    }
    return new String(code);
  }

  public static void main(String[] args) {
    HashSet<String> hashSet = new HashSet<>(100);
    for (int i = 0; i < 100; i++) {
      String code = generateVerificationCode();
      System.out.println(code);
      hashSet.add(code);
    }
    System.out.println(hashSet.size());
  }
}
