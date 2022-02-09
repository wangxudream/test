package com.kataer.localdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class LocalDateTest {
  public static void main(String[] args) {
    LocalDateTime today = LocalDateTime.now();
    System.out.println(today);
    Integer integer = 1;
    System.out.println(Objects.equals(integer,1));
  }
}
