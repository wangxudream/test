package com.kataer.collections;

import java.util.ArrayList;
import java.util.List;

public class CollectionDemo2 {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("1");
        strList.add("2");
        strList.add("3");
        strList.add("4");

        for (int i = 0; i < strList.size(); i++) {
            if ("1".equals(strList.get(i))) {
                strList.remove(strList.get(i));
            }
        }
//
//        new Thread(() -> {
//            for (String s : strList) {
//                if (s.equals("1")) {
//                    strList.remove(s);
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (String s : strList) {
//                if (s.equals("1")) {
//                    strList.remove(s);
//                }
//            }
//        }).start();

        for (String s : strList) {
            if (s.equals("1")) {
                strList.remove(s);
            }
        }

        System.out.println(strList);

    }
}
