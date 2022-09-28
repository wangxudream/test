package com.kataer;

import java.util.*;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/9/27
 */
public class Solution {
  public static void main(String[] args) {
    Solution solution = new Solution();
    String str = "pwwkew";
    int i = solution.lengthOfLongestSubstring(str);
    System.out.println(i);
  }

  public int lengthOfLongestSubstring(String s) {
    char[] chars = s.toCharArray();
    HashSet<Character> exists = new HashSet<>();
    int maxLen = 1;
    int index = 0;
    for (int i = 0; i < chars.length; i++) {
      char aChar = chars[i];
      if (exists.contains(aChar)) {
        maxLen = Math.max(maxLen, i - index);
        index++;
      } else {
        exists.add(aChar);
        if (i == chars.length - 1) {
          maxLen = Math.max(maxLen, i - index + 1);
        }
      }
    }
    return maxLen;
  }

//  public int lengthOfLongestSubstring(String s) {
//    char[] chars = s.toCharArray();
//    Map<String, List<Integer>> map = new HashMap<>();
//    for (int i = 0; i < chars.length; i++) {
//      List<Integer> indexList = map.getOrDefault(chars[i], new ArrayList<>());
//      indexList.add(i);
//    }
//    int maxLen = 1;
//    for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
//      List<Integer> value = entry.getValue();
//      maxLen = Math.max(value.get(value.  ))
//    }
//  }
}
