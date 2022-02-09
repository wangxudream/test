package com.kataer;

import java.math.BigInteger;

public class BigIntegerTest {
  public static void main(String[] args) {
    BigInteger seq = new BigInteger("100000");
    BigInteger divide = seq.multiply(new BigInteger("2"));

    BigInteger one = new BigInteger("1");
    BigInteger one2 = new BigInteger("1");
    BigInteger two = new BigInteger("2");
    System.out.println(two.compareTo(one2));
  }
}
