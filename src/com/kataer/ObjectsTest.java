package com.kataer;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class ObjectsTest {
    public static void main(String[] args) {
        float a = 0.1f;
        float b = 0.1f;
        float c = 0.9f - 0.8f;
        if (a == b) {
            System.out.println("a==b");
        }

        if (a == c) {
            System.out.println("a==c");
        }
        BigDecimal b1 = new BigDecimal("0.1");
        BigDecimal b2 = new BigDecimal("0.8");
        BigDecimal b3 = new BigDecimal("0.9");

        BigDecimal b4 = b3.subtract(b2);

        if (Objects.equals(b1, b4)) {
            System.out.println("b1==b4");
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        //不要使用BigDecimal的float和double的构造方法,使用String的构造方法或则valueOf方法
        BigDecimal c1 = new BigDecimal(0.1f);
        BigDecimal c2 = new BigDecimal(0.1D);
        BigDecimal c3 = BigDecimal.valueOf(0.1f);
        BigDecimal c4 = BigDecimal.valueOf(0.1);
        //0.100000001490116119384765625
        System.out.println(c1);
        //0.1000000000000000055511151231257827021181583404541015625
        System.out.println(c2);
        //0.1
        System.out.println(c3);
        //0.10000000149011612
        System.out.println(c4);

        long time = System.currentTimeMillis();
        Date date = new Date(time);
        System.out.println(date);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        int days2021 = LocalDate.now().lengthOfYear();
        int days2020 = LocalDate.of(2020, 1, 3).lengthOfYear();
        System.out.println(days2020);
        System.out.println(days2021);
    }
}