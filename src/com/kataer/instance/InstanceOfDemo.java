package com.kataer.instance;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstanceOfDemo {
    private static final Pattern pattern = Pattern.compile("^\\d+");

    public static void main(String[] args) {
        System.out.println(test("013.6"));
    }

    public static String test(String str) {
        Matcher matcher = pattern.matcher(str);
        boolean matches = matcher.matches();
        return matches ? null : "支付金额不合规";
    }
}
