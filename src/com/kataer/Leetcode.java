package com.kataer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode {
    public static void main(String[] args) {
        int[] A = new int[]{2, 2};
        int[] B = new int[]{4, 4};
        int[] ints = fairCandySwap2(A, B);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] fairCandySwap(int[] A, int[] B) {
        int ASum = 0;
        for (int a : A) {
            ASum += a;
        }

        int BSum = 0;
        for (int b : B) {
            BSum += b;
        }
        int[] res = new int[2];
        if ((ASum + BSum) % 2 != 0) {
            return res;
        }

        int dif = (ASum - BSum) / 2;


        for (int a : A) {
            for (int b : B) {
                if (a - b == dif) {
                    res[0] = a;
                    res[1] = b;
                    return res;
                }
            }
        }
        return res;
    }

    public static int[] fairCandySwap2(int[] A, int[] B) {
        //stream
        int ASum = Arrays.stream(A).sum();
        int BSum = Arrays.stream(B).sum();
        //两数之和
        Set<Integer> set = new HashSet<>(A.length);
        for (int a : A) {
            set.add(a);
        }

        int[] res = new int[2];
        int diff = (ASum - BSum) / 2;
        for (int b : B) {
            if (set.contains(b + diff)) {
                res[0] = b + diff;
                res[1] = b;
                return res;
            }
        }
        return res;
    }
}
