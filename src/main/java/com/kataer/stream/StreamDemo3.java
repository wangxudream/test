package com.kataer.stream;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo3 {
  public static void main(String[] args) {
    List<Data> dataList = Arrays.asList(
        new Data(9, "紫萱"),
        new Data(10, "子轩"),
        new Data(9, "雨轩"),
        new Data(11, "赵铁柱"),
        new Data(11, "子轩"),
        new Data(9, "雨轩"));
    List emptyList = Collections.EMPTY_LIST;
    max(dataList);
    maxNull(emptyList);
    distinctEmpty(emptyList);
    distinctNameCount(dataList);
    distinctName(dataList);
    distinctAge(dataList);
    filterAge(dataList);
    findFirst(dataList);
    mapToInt(dataList);
    distinctAll(dataList);
    anyMatch(dataList);
    findAny(dataList);
    skip(dataList);
//    peek(dataList);
    sum(dataList);
    avg(dataList);
    groupingBy(dataList);
    grouping2By(dataList);
    partitioningBy(dataList);
    joining(dataList);
  }


  public static void max(List<Data> dataList) {
    Data data = dataList.stream().max(Comparator.comparingInt(Data::getAge)).get();
    System.out.println(data);
  }

  public static void maxNull(List<Data> dataList) {
    Optional<Data> optional = dataList.stream().max(Comparator.comparingInt(Data::getAge));
    System.out.println(optional);
  }

  public static void distinctEmpty(List<Data> dataList) {
    long count = dataList.stream().map(Data::getName).distinct().count();
    System.out.println("distinctEmpty:" + count);
  }

  public static void distinctNameCount(List<Data> dataList) {
    long count = dataList.stream().map(Data::getName).distinct().count();
    System.out.println(count);
  }

  public static void distinctName(List<Data> dataList) {
    List<String> nameList = dataList.stream().map(Data::getName).distinct().collect(Collectors.toList());
    System.out.println(nameList);
  }

  public static void distinctAll(List<Data> dataList) {
    List<Data> collect = dataList.stream().distinct().collect(Collectors.toList());
    System.out.println("distinctAll:" + collect);
  }

  public static void distinctAge(List<Data> dataList) {
    List<Integer> ageList = dataList.stream().map(Data::getAge).distinct().collect(Collectors.toList());
    System.out.println(ageList);
  }

  public static void filterAge(List<Data> dataList) {
    List<Data> collect = dataList.stream().filter(v -> Objects.equals(v.getAge(), 9)).collect(Collectors.toList());
    System.out.println("filterAge:" + collect);
  }

  public static void findFirst(List<Data> dataList) {
    Data data = dataList.stream().findFirst().get();
    System.out.println("findFirst:" + data);
  }

  public static void mapToInt(List<Data> dataList) {
    int ageSum = dataList.stream().mapToInt(Data::getAge).sum();
    System.out.println("mapToInt:" + ageSum);
  }

  public static void anyMatch(List<Data> dataList) {
    boolean b = dataList.stream().anyMatch(data -> Objects.equals(data.getName(), "赵铁柱"));
    System.out.println("anyMatch:" + b);
  }


  public static void findAny(List<Data> dataList) {
    Data data = dataList.stream().findAny().get();
    System.out.println("findAny:" + data);
  }


  public static void skip(List<Data> dataList) {
    List<Data> collect1 = dataList.stream().skip(10).collect(Collectors.toList());
    System.out.println("skip_1:" + collect1);
    List<Data> collect2 = dataList.stream().skip(2).collect(Collectors.toList());
    System.out.println("skip_2:" + collect2);
  }


  public static void peek(List<Data> dataList) {
    //会改变原数据
    List<Data> collect = dataList.stream().peek(data -> data.setAge(100)).collect(Collectors.toList());
    System.out.println("peek:" + collect);
    System.out.println("peek:" + dataList);
  }


  public static void sum(List<Data> dataList) {
    int sum = dataList.stream().mapToInt(Data::getAge).sum();
    Integer collect = dataList.stream().collect(Collectors.summingInt(Data::getAge));
    System.out.println("sum:" + sum);
    System.out.println("sum:" + collect);
  }


  public static void avg(List<Data> dataList) {
    double asDouble = dataList.stream().mapToInt(Data::getAge).average().getAsDouble();
    Double collect = dataList.stream().collect(Collectors.averagingDouble(Data::getAge));
    System.out.println("avg:" + asDouble);
    System.out.println("avg:" + collect);
  }

  public static void groupingBy(List<Data> dataList) {
    Map<Integer, List<Data>> collect = dataList.stream().collect(Collectors.groupingBy(Data::getAge));
    System.out.println("groupingBy:" + collect);
  }

  public static void grouping2By(List<Data> dataList) {
    Map<Integer, Map<String, List<Data>>> collect = dataList.stream().collect(Collectors.groupingBy(Data::getAge, Collectors.groupingBy(Data::getName)));
    System.out.println("grouping2By:" + collect);
  }


  public static void partitioningBy(List<Data> dataList) {
    Map<Boolean, List<Data>> collect = dataList.stream().collect(Collectors.partitioningBy(v -> v.getAge() > 10));
    System.out.println("collect:" + collect);
  }

  public static void joining(List<Data> dataList) {
    String collect1 = dataList.stream().filter(v -> v.getAge() > 10).map(Data::getName).collect(Collectors.joining(","));
    String collect2 = dataList.stream().map(Data::getName).collect(Collectors.joining(",", "[", "]"));
    System.out.println("joining:" + collect1);
    System.out.println("joining:" + collect2);
  }


  private static class Data {
    private Integer age;
    private String name;

    public Data(Integer age, String name) {
      this.age = age;
      this.name = name;
    }

    public Integer getAge() {
      return age;
    }

    public void setAge(Integer age) {
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "Data{" +
          "age=" + age +
          ", name='" + name + '\'' +
          '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Data)) {
        return false;
      }
      Data data = (Data) o;
      return getAge().equals(data.getAge()) &&
          getName().equals(data.getName());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getAge(), getName());
    }
  }
}
