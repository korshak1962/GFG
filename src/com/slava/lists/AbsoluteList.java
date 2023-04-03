package com.slava.lists;

import com.slava.lists.IsListPalindrome.Node;
import org.junit.Test;
//https://practice.geeksforgeeks.org/problems/absolute-list-sorting/1
public class AbsoluteList {

    @Test
    public void test1() {
        Node root = new Node(1);
        root.next = new Node(-2);
        root.next.next = new Node(-3);
        root.next.next.next = new Node(4);
        final Node start = sortList(root);
        System.out.println(start);
    }

    Node sortList(Node current) {
        if (current == null) return null;
        Node start = current;
        Node next;
        Node prev = current;
        current = current.next;
        while (current != null) {
            next = current.next;
            if (current.data < 0) {
                prev.next = next;
                current.next = start;
                start = current;
            } else {
                prev = current;
            }
            current = next;
        }
        return start;
    }


}
