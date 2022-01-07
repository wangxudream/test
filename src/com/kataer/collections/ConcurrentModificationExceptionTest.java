package com.kataer.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author kataer
 * 测试容器的遍历和元素的删除
 * 1、for i循环
 * 2、foreach 增强循环
 * 3、迭代器循环
 * 解决方案
 * 1、使用迭代器的删除方法
 * 2、使用stream流做处理
 * 3、使用fail safe的容器 线程安全的容器
 */
public class ConcurrentModificationExceptionTest {
  public static void main(String[] args) {
    /**
     * 利用普通循环
     */
    List<String> strList = getList();
    for (int i = 0; i < strList.size(); i++) {
      if ("1".equals(strList.get(i))) {
        strList.remove(strList.get(i));
      }
    }
    //[1, 3, 4] 由于元素删除后size和下标会变化，造成漏删元素
    System.out.println(strList);


    /**
     * 利用增强循环
     * 增强循环是语法糖 实际是利用迭代器进行遍历
     *     List<String> strList2 = getList();
     *     //获取迭代器
     *     Iterator var3 = strList2.iterator();
     *
     *     while(var3.hasNext()) {
     *       String s = (String)var3.next();
     *       if ("1".equals(s)) {
     *         strList2.remove(s);
     *       }
     *     }
     */
    List<String> strList2 = getList();
    for (String s : strList2) {
      if ("1".equals(s)) {
        /**
         * ConcurrentModificationException
         * fast fail 机制
         * 调用fastRemove()时会修改modCount++[list的成员变量];
         * 调用迭代器的next方法会 checkForComodification();
         * final void checkForComodification() {
         *     if (modCount != expectedModCount)
         *          throw new ConcurrentModificationException();
         *}
         */
//        strList2.remove(s);
      }
    }

    /**
     * 利用迭代器
     *
     */
//    class ArrayList{
//      private int modCount;
//      public void add();
//      public void remove();
//      private class Itr implements Iterator<E> {
//        int expectedModCount = modCount;
//      }
//      public Iterator<E> iterator() {
//        return new Itr();
//      }
//    }
    List<String> strList3 = getList();
    Iterator<String> iterator = strList3.iterator();
    while (iterator.hasNext()) {
      if ("1".equals(iterator.next())) {
        // expectedModCount = modCount;
        iterator.remove();
      }
    }
    System.out.println(strList3);
  }

  public static List<String> getList() {
    List<String> strList = new ArrayList<>();
    strList.add("1");
    strList.add("1");
    strList.add("3");
    strList.add("4");
    return strList;
  }
}
