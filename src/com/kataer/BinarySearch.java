package com.kataer;

public class BinarySearch {
  private static int[][] dataArray = new int[12][2];

  public static void main(String[] args) {
    for (int i = 0; i < 12; i++) {
      int[] ints = new int[2];
      ints[0] = i;
      ints[1] = i + 1;
      dataArray[i] = ints;
    }

    int i = ensureOffset(6);
    System.out.println("result>>" + i);
  }

  private static int ensureOffset(int data) {
    int left = 0;
    int right = dataArray.length - 1;
    while (left <= right) {
      int mid = (left + right) >> 1;
      System.out.println("mid>>" + mid);
      if (data < dataArray[mid][0]) {
        right = mid - 1;
      } else if (data >= dataArray[mid][1]) {
        left = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }
}
