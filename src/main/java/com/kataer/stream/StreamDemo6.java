package com.kataer.stream;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamDemo6 {
  public static void main(String[] args) {
    User user1 = new User("a", 18);
    User user2 = new User("a", 17);
    User user3 = new User("b", 15);
    User user4 = new User("b", 8);
    List<User> userList = Arrays.asList(user1, user2, user3, user4);
    List<User> afterSort = userList.stream().sorted(Comparator.comparing(User::getName).thenComparing(User::getAge, Comparator.reverseOrder())).collect(Collectors.toList());
    System.out.println(afterSort);


    List<User> collect = userList.stream().filter(new Predicate<User>() {
      @Override
      public boolean test(User user) {
        return Objects.equals(user.getAge(), 10);
      }
    }).collect(Collectors.toList());
    System.out.println(collect.size());
    collect.add(user1);
  }
}
