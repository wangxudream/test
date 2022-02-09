package com.kataer.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试SubList
 * 1、相当于视图 修改数据会改变source数据
 * 2、SubList 为内部类，不能强制转换为ArrayList等类，因为其没有继承关系
 * 3、可以使用stream复制数据
 *
 * @author kataer
 * @date 2022/1/7
 */
public class SubListTest {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("0", "a", "2", "3", "4");
    //包头不包尾
    List<String> subList = list.subList(1, 3);
    System.out.println(subList);
    //set为原来的元素 下标为subList的下标
    String set = subList.set(0, "1");
    System.out.println(set);
    System.out.println(list);

    //使用stream创建新的容器
    List<String> collect = list.stream().skip(1).limit(2).collect(Collectors.toList());
    System.out.println("collect:" + collect);
//    //利用arraycopy
//    String[] dest = new String[3];
//    System.arraycopy(list.stream().toArray(), 1, dest, 1, 2);
//    System.out.println(Arrays.toString(dest));
  }
}
