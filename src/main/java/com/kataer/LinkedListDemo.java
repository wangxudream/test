package com.kataer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LinkedListDemo {

    public static void main(String[] args) {
        Node root = createNode();
        System.out.println(hasCircle(root));
        List list = circleLength(root);
        Node node = findEnter(root);
        System.out.println("入口节点:" + node);
        System.out.println("end>>>>>>>>>>>>>>>>>");
    }

    public static boolean hasCircle(Node root) {
        Node slowIndex = root;
        Node fastIndex = root;
        while (fastIndex.next != null && fastIndex.next.next != null) {
            slowIndex = slowIndex.next;
            fastIndex = fastIndex.next.next;
            if (slowIndex == fastIndex) {
                System.out.println("链表有环");
                return true;
            }
        }
        return false;
    }


    public static List<Node> circleLength(Node root) {
        ArrayList<Node> circleList = new ArrayList<>();
        Node slowIndex = root;
        Node fastIndex = root;
        boolean hasCircle = false;
        int count = 0;
        while (fastIndex.next != null && fastIndex.next.next != null) {
            if (hasCircle) {
                count++;
                circleList.add(slowIndex);
            }
            slowIndex = slowIndex.next;
            fastIndex = fastIndex.next.next;
            if (slowIndex == fastIndex) {
                hasCircle = true;
                if (count != 0) {
                    break;
                }
            }
        }
        System.out.println("环长度:" + count);
        return circleList;
    }

    public static Node findEnter(Node root) {
        boolean hasCircle = false;
        Node slowIndex = root;
        Node fastIndex = root;
        while (fastIndex.next != null && fastIndex.next.next != null) {
            slowIndex = slowIndex.next;
            fastIndex = fastIndex.next.next;
            if (slowIndex == fastIndex) {
                hasCircle = true;
                break;
            }
        }
        //发现有环后，一个指针从head开始，另一个指针从快慢指针相遇节点开始， 两个指针相遇的地点就是环入口
        if (hasCircle) {
            Node head = root;
            //或者fastIndex也可以
            Node index = slowIndex;
            while (head != index) {
                head = head.next;
                index = index.next;
            }
            return index;
        }
        return null;
    }


    public static Node findEnter2(Node root) {
        HashSet<Node> nodes = new HashSet<>();
        Node head = root;
        while (head.next != null) {
            head = head.next;
            if (nodes.contains(head)) {
                return head;
            }
            nodes.add(head);
        }
        return null;
    }

    public static Node createNode() {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        node0.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        //环位置
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(node9);
        node9.setNext(node4);
        return node0;
    }

    private static class Node {
        private Integer id;
        private Node next;

        public Node(Integer id) {
            this.id = id;
        }

        public Node(Integer id, Node next) {
            this.id = id;
            this.next = next;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';
        }
    }
}
