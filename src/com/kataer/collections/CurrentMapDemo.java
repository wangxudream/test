package com.kataer.collections;

public class CurrentMapDemo {
    public static void main(String[] args) {
        //concurrentHashMap的key和value都不允许为null
//        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<String, String>(16);
//        concurrentHashMap.put("A", null);
//        concurrentHashMap.put(null, "");'
        switchString(null);
        switchInteger(null);
        switchEnum(null);
        System.out.println("finish>>>>>>>>>>>>>>>>");
    }

    public static void switchString(String str) {
        switch (str) {
            case "A":
                System.out.println("A");
                break;
            case "B":
                System.out.println("B");
                break;
            default:
                System.out.println(str);
        }
    }

    public static void switchInteger(Integer integer) {
        switch (integer) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            default:
                System.out.println("default");
        }
    }

    public static void switchEnum(Gender gender) {
        switch (gender) {
            case MAN:
                System.out.println(1);
                break;
            case WOMEN:
                System.out.println(2);
                break;
            default:
                System.out.println("default");
        }
    }

}
