package com.kataer.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setName("user1");
        user1.setAge(18);
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
        //和user4相同
        User user5 = new User();
        user5.setName("user4");
        user5.setAge(21);
        user5.setGender(false);
        userList.add(user5);

        filter(userList);
        limit(userList);
        sort(userList);
        parallel(userList);
        collect(userList);
        toMap(userList);
    }

    public static void filter(List<User> users) {
        System.out.println("filter>>>>>>>");
        List<User> collect = users.stream()
                .filter(user -> user.getAge() != null)
                .filter(user -> user.getAge().intValue() == 18)
                .filter(user -> user.getGender()).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void limit(List<User> users) {
        System.out.println("limit>>>>>>>");
        List<User> collect = users.stream().limit(1).collect(Collectors.toList());
        System.out.println(collect);
    }


    public static void sort(List<User> users) {
        System.out.println("limit>>>>>>>");
        List<User> collect = users.stream().sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void collect(List<User> users) {
        System.out.println("collect>>>>>>>");
        String collect = users.stream().map(user -> user.getName()).collect(Collectors.joining(":"));
        System.out.println(collect);
    }

    public static void parallel(List<User> users) {
        System.out.println("parallel>>>>>>>");
        users.parallelStream().forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                //main:user3
                //main:user4
                //main:user1
                //ForkJoinPool.commonPool-worker-9:user2
                System.out.println(Thread.currentThread().getName() + ":" + user.getName());
            }
        });
    }

    //考虑map或者set中key相同时的情况的处理,否则会抛出异常
    public static void toMap(List<User> users) {
        System.out.println("toMap>>>>>>>>>>>>>>>>>>>>");
        Map<String, User> collect = users.stream().collect(Collectors.toMap(item -> item.getName(), item -> item, (v1, v2) -> v2));
        System.out.println(users);
        System.out.println(collect);
    }
}
