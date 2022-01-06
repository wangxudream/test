package com.kataer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.UUID;

public class LocalDateTimeTest {
  public static void main(String[] args) {
    java.time.LocalDateTime now = java.time.LocalDateTime.now();
    java.time.LocalDateTime localDateTime = now.plusSeconds(100);
    System.out.println(localDateTimeToTimestamp(now));
    System.out.println(localDateTimeToTimestamp(localDateTime));
    System.out.println(System.currentTimeMillis());
  }

  public static Long localDateTimeToTimestamp(java.time.LocalDateTime localDateTime) {
    try {
      ZoneId zoneId = ZoneId.systemDefault();
      Instant instant = localDateTime.atZone(zoneId).toInstant();
      return instant.toEpochMilli();//毫秒时间戳
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
