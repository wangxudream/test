package com.kataer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDemo {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = "2021-02-21 00:00:00";
        Date date1 = dateFormat.parse(date);
        System.out.println(date1.getTime());
    }
}
