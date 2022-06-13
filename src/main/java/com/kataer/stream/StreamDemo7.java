package com.kataer.stream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kataer
 * @description: flatMap
 * @date 2022/3/3
 */
public class StreamDemo7 {

  public static void main(String[] args) {
    try {
      AccountThreadLocal.remove();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static class AccountThreadLocal {
    private static final ThreadLocal<Account> SYS_ACCOUNT = new ThreadLocal<>();

    public static void set(Account sysAccount) {
      SYS_ACCOUNT.set(sysAccount);
    }

    public static Account get() {
      return SYS_ACCOUNT.get();
    }

    public static void remove() {
      SYS_ACCOUNT.remove();
    }
  }

  public static class Account {

  }
}
