package com.kataer;

import org.apache.commons.math3.linear.ArrayRealVector;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/5/18
 */
public class GroupMatch {
  public static void main(String[] args) {
    String[] nameArray = new String[]{"AA", "AA", "BB", "CC", "DD", "EE"};
    List<String> groupA = Arrays.asList("AA", "BB", "CC", "EE", "EE");
    List<String> groupB = Arrays.asList("AA", "BB", "CC", "EE");
    List<String> groupC = Arrays.asList("CC", "DD", "EE");
    List<String> groupD = Arrays.asList("KK", "KK", "KK");
    Map<String, Long> groupAMap = groupA.stream().collect(Collectors.groupingBy(String::toString, Collectors.counting()));
    Map<String, Long> groupBMap = groupB.stream().collect(Collectors.groupingBy(String::toString, Collectors.counting()));
    Map<String, Long> groupCMap = groupC.stream().collect(Collectors.groupingBy(String::toString, Collectors.counting()));
    Map<String, Long> groupDMap = groupD.stream().collect(Collectors.groupingBy(String::toString, Collectors.counting()));
    double[] arrayA = new double[nameArray.length];
    double[] arrayB = new double[nameArray.length];
    double[] arrayC = new double[nameArray.length];
    double[] arrayD = new double[nameArray.length];
    for (int i = 0; i < nameArray.length; i++) {
      Long countA = groupAMap.get(nameArray[i]);
      arrayA[i] = countA == null ? 0 : countA.intValue();

      Long countB = groupBMap.get(nameArray[i]);
      arrayB[i] = countB == null ? 0 : countB.intValue();

      Long countC = groupCMap.get(nameArray[i]);
      arrayC[i] = countC == null ? 0 : countC.intValue();

      Long countD = groupDMap.get(nameArray[i]);
      arrayD[i] = countD == null ? 0 : countD.intValue();
    }
    System.out.println(Arrays.toString(arrayA));
    System.out.println(Arrays.toString(arrayB));
    System.out.println(Arrays.toString(arrayC));
    System.out.println(Arrays.toString(arrayD));
    ArrayRealVector vectorA = new ArrayRealVector(arrayA);
    ArrayRealVector vectorB = new ArrayRealVector(arrayB);
    ArrayRealVector vectorC = new ArrayRealVector(arrayC);
    ArrayRealVector vectorD = new ArrayRealVector(arrayD);
    System.out.println(vectorA.dotProduct(vectorB) / (vectorA.getNorm() * vectorB.getNorm()));
    System.out.println(vectorA.dotProduct(vectorC) / (vectorA.getNorm() * vectorC.getNorm()));
    System.out.println(vectorA.dotProduct(vectorD) / (vectorA.getNorm() * vectorD.getNorm()));
  }
}
