package com.kataer;

import java.security.MessageDigest;
import java.util.*;

public class HYKUtils {
    private static String appId = "hyk1615860284987";
    private static String key = "816B077500184157AD977C72D793C885";

    private static Map<String, Object> paramMap = new HashMap<>();

    static {
        paramMap.put("appId", appId);
        paramMap.put("key", key);
        paramMap.put("pageNo", 1);
        paramMap.put("pageSize", 30);
    }

    public static void main(String[] args) throws Exception {
        createSign();
        createSign2();
        createSign3();
    }

    //会场列表
    public static void createSign() throws Exception {
        String uuid = UUID.randomUUID().toString();
        System.out.println("randomStr:" + uuid);
        ArrayList<String> params = new ArrayList<>();
        params.add("exhibitionParkType");
        params.add("pageNo");
        params.add("pageSize");
        params.add("appId");
        params.add("randomStr");
        params.add("gmtRequest");
        params.add("time");
        params.sort(String::compareTo);
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
        String a = "appId=hyk1615860284987&exhibitionParkType=1&gmtRequest=" + time + "&pageNo=1&pageSize=20&randomStr=string";
        String signTemp = a + "&key=816B077500184157AD977C72D793C885";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(signTemp.getBytes());
        String s = new String(digest, "utf8");
        System.out.println(s);
        String result = "";
        for (byte b : digest) {
            String temp = Integer.toHexString(b & 0xff);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            result += temp;
        }

        System.out.println(result.toUpperCase());

    }

    //会场商品列表
    public static void createSign2() throws Exception {
        String uuid = UUID.randomUUID().toString();
        ArrayList<String> params = new ArrayList<>();
        params.add("exhibitionParkId");
        params.add("pageNo");
        params.add("pageSize");
        params.add("appId");
        params.add("randomStr");
        params.add("gmtRequest");
        params.sort(String::compareTo);
        System.out.println(params);
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
        String a = "appId=hyk1615860284987&exhibitionParkId=100308879&gmtRequest=" + time + "&pageNo=1&pageSize=10&randomStr=string";
        String signTemp = a + "&key=816B077500184157AD977C72D793C885";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(signTemp.getBytes());
        String s = new String(digest, "utf8");
        System.out.println(s);
        String result = "";
        for (byte b : digest) {
            String temp = Integer.toHexString(b & 0xff);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            result += temp;
        }

        System.out.println(result.toUpperCase());

    }


    //会场商品列表
    public static void createSign3() throws Exception {
        String uuid = UUID.randomUUID().toString();
        ArrayList<String> params = new ArrayList<>();
        params.add("pitemId");
        params.add("pageNo");
        params.add("pageSize");
        params.add("appId");
        params.add("randomStr");
        params.add("gmtRequest");
        params.sort(String::compareTo);
        System.out.println(params);
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
        String a = "appId=hyk1615860284987&gmtRequest=" + time + "&pageNo=1&pageSize=10&pitemId=227638&randomStr=string";
        String signTemp = a + "&key=816B077500184157AD977C72D793C885";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(signTemp.getBytes());
        String s = new String(digest, "utf8");
        System.out.println(s);
        String result = "";
        for (byte b : digest) {
            String temp = Integer.toHexString(b & 0xff);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            result += temp;
        }

        System.out.println(result.toUpperCase());

    }


}
