package com.kataer.optional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class OptionalTest {
  public static void main(String[] args) {
//    User user = null;
//    Optional.ofNullable(user).ifPresent(new Consumer<User>() {
//      @Override
//      public void accept(User user) {
//        System.out.println("aaa");
//      }
//    });
//    op.ifPresent(new Consumer<User>() {
//      @Override
//      public void accept(User user) {
//        System.out.println("aaaa");
//      }
//    });
//    User user1 = Optional.ofNullable(user).orElse(new User("bbb"));
//    System.out.println(user1);

    List<String> list = Arrays.asList("aaa");
    List<String> stringList = Optional.ofNullable(list).orElse(Collections.emptyList());
    System.out.println(stringList);
  }

  public static void empty() {
    try {
      Optional<String> empty = Optional.empty();
      empty.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
