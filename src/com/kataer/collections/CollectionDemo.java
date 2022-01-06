package com.kataer.collections;

import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("A");
        strList.add("B");
        Object[] objList = strList.toArray();
        String[] strArray = strList.toArray(new String[0]);
        System.out.println(Arrays.toString(strArray));
        //
        HashSet<String> strSet = null;
        ArrayList<String> strings = new ArrayList<>();
        strings.addAll(strSet);
        //
    }
}
