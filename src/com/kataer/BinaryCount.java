package com.kataer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryCount {
  public static void main(String[] args) throws Exception {
//    LocalDate of = LocalDate.of(2021, 6, 27);
//    DayOfWeek dayOfWeek1 = of.getDayOfWeek();
//    System.out.println(dayOfWeek1);
//    LocalDate now = LocalDate.now();
//    DayOfWeek dayOfWeek = now.getDayOfWeek();
//    int value = dayOfWeek.getValue();
//    System.out.println(value);

//    String data = "123456" + " test ";
//    MessageDigest md5 = MessageDigest.getInstance("MD5");
//    byte[] array = md5.digest(data.getBytes("UTF-8"));
//    StringBuilder sb = new StringBuilder();
//    for (byte item : array) {
//      sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
//    }
//    System.out.println(sb.toString());

    Node node1 = new Node(1, 0);
    Node node2 = new Node(2, 0);
    Node node3 = new Node(3, 0);
    Node node4 = new Node(4, 2);
    Node node5 = new Node(5, 2);
    Node node6 = new Node(6, 4);
    Node node7 = new Node(7, 2);
    ArrayList<Node> nodes = new ArrayList<>();
    nodes.add(node1);
    nodes.add(node2);
    nodes.add(node3);
    nodes.add(node4);
    nodes.add(node5);
    nodes.add(node6);
    nodes.add(node7);

    List<Node> superNodes = nodes.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());
    System.out.println(superNodes);
    filter(nodes, superNodes);
    System.out.println(superNodes);
  }

  public static void filter(List<Node> source, List<Node> superNodes) {
    for (Node target : superNodes) {
      List<Node> collect = source.stream().filter(item -> item.getParentId() == target.getId()).collect(Collectors.toList());
      if (!collect.isEmpty()) {
        target.setChild(collect);
        filter(source, collect);
      }
    }
  }

  private static class Node {
    private int id;
    private int parentId;
    private List<Node> child;

    public Node(int id, int parentId) {
      this.id = id;
      this.parentId = parentId;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public int getParentId() {
      return parentId;
    }

    public void setParentId(int parentId) {
      this.parentId = parentId;
    }

    public List<Node> getChild() {
      return child;
    }

    public void setChild(List<Node> child) {
      this.child = child;
    }

    @Override
    public String toString() {
      return "Node{" +
          "id=" + id +
          ", parentId=" + parentId +
          ", child=" + child +
          '}';
    }
  }
}
