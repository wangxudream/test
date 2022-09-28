package com.kataer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/9/15
 */
public class Test {
  public static void main(String[] args) {
//    Test test = new Test();
//    int n = 4;
//    int i = test.climbStairs(n);
//    System.out.println(i);

    Test test = new Test();
//    List<List<Integer>> generate = test.generate(5);
//    List<List<Integer>> generate = test.generate_2(5);
//    List<Integer> row = test.getRow(5);
//    List<Integer> row_2 = test.getRow_2(5);
//    System.out.println(row);
//    System.out.println(row_2);
//    System.out.println(generate);
//    System.out.println(test.maxProfit_2(new int[]{7, 6, 5, 8, 3, 2, 100}));
//    int[] ints = test.countBits(5);
//    System.out.println(Arrays.toString(ints));
//    test.bitCount();
//    System.out.println(test.bitCount_1(-5));
//    System.out.println(test.bitCount_2(-5));
////    System.out.println(Integer.toBinaryString(1));
////    System.out.println(Integer.toBinaryString(-2));
////    System.out.println(Integer.highestOneBit(5));
//    System.out.println(Integer.toBinaryString(-5));
//    System.out.println(Integer.toBinaryString(256 - 5));
    boolean subsequence = test.isSubsequence("abc", "akbcd");
    System.out.println(subsequence);
  }

  /**
   * 斐波那契数列，跳楼梯
   *
   * @param n
   * @return
   */
  public int climbStairs(int n) {
    if (n <= 2) {
      return n;
    }
    return climbStairs(n - 1) + climbStairs(n - 2);
  }

  /**
   * 杨辉三角
   *
   * @param numRows
   * @return
   */
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> res = new ArrayList<>();
    generateRow(res, 1, numRows);
    return res;
  }

  private void generateRow(List<List<Integer>> res, int rowNum, int high) {
    if (rowNum > high) {
      return;
    }
    List<Integer> row = new ArrayList<>(rowNum);
    if (rowNum == 1) {
      row.add(1);
    } else {
      List<Integer> preRow = res.get(rowNum - 2);
      for (int i = 0; i < rowNum; i++) {
        if (i == 0) {
          row.add(i, 1);
        } else if (i == rowNum - 1) {
          row.add(i, 1);
        } else {
          row.add(preRow.get(i - 1) + preRow.get(i));
        }
      }
    }
    res.add(row);
    generateRow(res, ++rowNum, high);
  }


  /**
   * 杨辉三角
   *
   * @param numRows
   * @return
   */
  public List<List<Integer>> generate_2(int numRows) {
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 1; i <= numRows; i++) {
      List<Integer> row = new ArrayList<>(i);
      for (int j = 0; j < i; j++) {
        if (j == 0 || j == i - 1) {
          row.add(1);
        } else {
          //取上一行的j和j-1个元素的和
          row.add(j, res.get(i - 2).get(j) + res.get(i - 2).get(j - 1));
        }
      }
      res.add(row);
    }
    return res;
  }

  /**
   * 杨辉三角2 多个list替换
   *
   * @param numRows
   * @return
   */
  public List<Integer> getRow(int numRows) {
    List<Integer> pre = new ArrayList<>(1);
    for (int i = 0; i <= numRows; i++) {
      List<Integer> row = new ArrayList<>(i + 1);
      for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i) {
          row.add(1);
        } else {
          //取上一行的j和j-1个元素的和
          row.add(j, pre.get(j) + pre.get(j - 1));
        }
      }
      pre = row;
    }
    return pre;
  }

  /**
   * 杨辉三角2 使用一个list
   *
   * @param numRows
   * @return
   */
  public List<Integer> getRow_2(int numRows) {
    List<Integer> res = new ArrayList<>();
    res.add(1);
    for (int i = 1; i <= numRows; i++) {
      res.add(0);
      for (int j = i; j > 0; j--) {
        res.set(j, res.get(j) + res.get(j - 1));
        System.out.println(res);
      }
    }
    return res;
  }

  /**
   * 超出时间限制
   *
   * @param prices
   * @return
   */
  public int maxProfit(int[] prices) {
    int days = prices.length;
//    int[][] profit = new int[days][days];
    int maxProfit = 0;
    for (int i = 0; i < days; i++) {
      for (int j = i + 1; j < days; j++) {
//        profit[i][j] = prices[j] - prices[i];
        maxProfit = Math.max(prices[j] - prices[i], maxProfit);
      }
    }
    return maxProfit;
  }

  /**
   * 假设要在第i天卖出股票获取最大收益，则需在[0,i-1]天内的最低值买入股票
   *
   * @param prices
   * @return
   */
  public int maxProfit_3(int[] prices) {
    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;
    for (int i = 0; i < prices.length; i++) {
      if (prices[i] < minPrice) {
        minPrice = prices[i];
      } else if (prices[i] - minPrice > maxProfit) {
        maxProfit = prices[i] - minPrice;
      }
    }
    return maxProfit;
  }

  /**
   * 动态规划
   * dp[i] = min(dp[i-1],prices[i])
   * 第i天前的最小值为i-1天最小值和prices[i]对比
   *
   * @param prices
   * @return
   */
  public int maxProfit_2(int[] prices) {
    int[] dp = new int[prices.length];
    int maxProfit = 0;
    dp[0] = prices[0];
    for (int i = 1; i < prices.length; i++) {
      dp[i] = Math.min(dp[i - 1], prices[i]);
      maxProfit = Math.max((prices[i] - dp[i]), maxProfit);
    }
    return maxProfit;
  }

  /**
   * x&(x-1)>0的次数就是bit为1次数
   *
   * @param n
   * @return
   */
//  public int[] countBits(int n) {
//
//  }

  /**
   * @param x
   * @return
   */
  public int bitCount_1(int x) {
    int ones = 0;
    while (x != 0) {
      ones++;
      x &= (x - 1);
    }
    return ones;
  }

  public int bitCount_2(int x) {
    int ones = 0;
    for (int i = 0; i < 32; i++) {
      if ((x & 1) == 1) {
        ones++;
      }
      x >>= 1;
    }
    return ones;
  }

  public boolean isSubsequence(String s, String t) {
    if (s.length() == 0) {
      return true;
    }
    char[] source = t.toCharArray();
    char[] target = s.toCharArray();
    int charIndex = 0;
    int searchIndex = 0;
    int sourceLen = source.length;
    int targetLen = target.length;
    while (searchIndex < sourceLen && charIndex < targetLen) {
      if (source[searchIndex] == target[charIndex]) {
        if (charIndex == targetLen - 1) {
          return true;
        }
        charIndex++;
      }
      searchIndex++;
    }
    return false;
  }

//  public String aaa(String source){
//    char[] chars = source.toCharArray();
//
//  }


//  public boolean divisorGame(int n) {
//
//  }

}
