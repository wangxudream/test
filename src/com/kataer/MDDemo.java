package com.kataer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MDDemo {
    public static void main(String[] args) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
