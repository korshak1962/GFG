package com.slava.lists;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ListReversingAndReorder {


    Node reorderlist(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        int limit = count / 2 + 1;
        count = 0;
        Node counterHead = null;
        while (head != null) {
            count++;
            if (count == limit) {
                counterHead = ListReversingAndReorder.reverse(head);
            }
            head = head.next;
        }
        return null;
    }

    @Test
    public void testNoop() {
        Map<Integer,Integer> map=new HashMap<>();
        Map<Integer,Integer> map1=new HashMap<>();
        map.put(1,1);map1.put(1,1);
        System.out.println(map.equals(map1));


        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(6);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(10);

        head = reverse(head);
        while (head.next != null) {
            System.out.println(head.value);
            head = head.next;
        }
        System.out.println(head.value);
    }


    public static Node reverse(Node head) {
        Node previous = head;
        head = head.next;
        previous.next = null;
        Node next;
        while (head.next != null) {
            next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        head.next = previous;
        return head;
    }

    public static class Node {
        int value = 0;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }
}
