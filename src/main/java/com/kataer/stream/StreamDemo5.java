package com.kataer.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo5 {
  public static void main(String[] args) {
    User user1 = new User();
    user1.setName("A");
    User user2 = new User();
    user2.setName("B");
    List<User> userList = Arrays.asList(user1, user2);
    Map<String, Integer> collect = userList.stream().collect(Collectors.toMap(item -> item.getName(), item -> item.getAge()));
    HashMap<Long, String> map = new HashMap<>();
    map.put(1L, null);
    map.put(2L, null);
  }
}
