package com.kataer;


import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        String s = "abbxxxxzyy";
        List<List<Integer>> lists = largeGroupPositions(s);
        System.out.println(lists);
    }

    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == chars[start]) {
            } else {
                if ((i - start) >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(i - 1);
                    res.add(list);
                }
                start = i;
            }
        }

        if ((chars.length - start) >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(chars.length - 1);
            res.add(list);
        }

        return res;
    }
}
