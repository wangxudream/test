package com.kataer;


import java.util.Deque;
import java.util.LinkedList;

public class ThreadPool {
    public static void main(String[] args) {
//        System.out.println(isPalindrome4(10));
        System.out.println(isValid("([)]"));
    }

    public static boolean isPalindrome(int x) {
        String str = x + "";
        int left, right;
        if (str.length() % 2 == 0) {
            //偶数
            left = (str.length() - 1) / 2;
            right = (str.length() - 1) / 2 + 1;
        } else {
            //奇数
            left = (str.length() - 1) / 2 - 1;
            right = (str.length() - 1) / 2 + 1;
        }
        while (left >= 0 && right < str.length()) {
            if (str.charAt(left) == str.charAt(right)) {
                left--;
                right++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        String str = x + "";
        String rStr = new StringBuilder(str).reverse().toString();
        if (rStr.equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        }
        String str = x + "";
        char[] chars = str.toCharArray();
        int mid = (chars.length - 1) / 2;
        for (int i = 0; i <= mid; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    //不开辟内存空间
    public static boolean isPalindrome4(int x) {
        //能被10取模为0且不为0
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int rev = 0;
        while (rev < x) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        //x == rev / 10 长度为奇数的情况
        if (x == rev || x == rev / 10) {
            return true;
        }
        return false;
    }


    //利用Deque
    public static boolean isValid(String s) {
        int len = s.length();
        if (len % 2 == 1) {
            return false;
        }
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                deque.offerLast(ch);
            } else {
                if (ch == ')') {
                    if (deque.pollLast() != '(') {
                        return false;
                    }
                } else if (ch == ']') {
                    if (deque.pollLast() != '[') {
                        return false;
                    }
                } else {
                    if (deque.pollLast() != '{') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
