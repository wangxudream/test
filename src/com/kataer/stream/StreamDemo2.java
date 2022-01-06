package com.kataer.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo2 {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setName("user1");
        user1.setAge(null);
        user1.setGender(true);
        userList.add(user1);

        User user2 = new User();
        user2.setName("user2");
        user2.setAge(18);
        user2.setGender(false);
        userList.add(user2);

        User user3 = new User();
        user3.setName("user3");
        user3.setAge(23);
        user3.setGender(true);
        userList.add(user3);

        User user4 = new User();
        user4.setName("user4");
        user4.setAge(21);
        user4.setGender(false);
        userList.add(user4);

        User user5 = null;
        userList.add(user5);

        toMap(userList);

        List emptyList = Collections.EMPTY_LIST;
        emptyList.add(1);
        System.out.println(emptyList);
    }

    public static void toMap(List<User> users) {
        //java.util.HashMap.merge value为null时会抛出NPE
        Map<String, Integer> collect = users.stream().filter(user -> {
            if (user == null) {
                return false;
            }
            if (user.getAge() == null) {
                user.setAge(0);
            }
            return true;
        }).collect(Collectors.toMap(user -> user.getName(), user -> user.getAge(), (u1, u2) -> u2));
        System.out.println(collect);
    }
}
