package com.slava.lists;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

//https://practice.geeksforgeeks.org/problems/check-if-linked-list-is-pallindrome/1/?problemType=functional&page=1&sortBy=submissions&query=problemTypefunctionalpage1sortBysubmissions#
public class IsListPalindrome {

    static class Node
    {
        int data;
        Node next;
        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    @Test
    public void test1() {
        Node root = new Node(10);
        root.next = new Node(12);
        root.next.next = new Node(10);
 //       root.next.next.next = new Node(10);
        System.out.println(isPalindrome(root));
    }

    boolean isPalindrome( Node head)
    {
        if (head == null  ) return false;
        Deque<Integer> deque= new LinkedList<>();
        deque.add(head.data);
        Node next = head.next;
        if (next == null  ) return true;
        deque.add(next.data);
        while (next.next != null) {
            next = next.next;
            deque.add(next.data);
        }
        while (head.next != null) {
            if (head.data != deque.pollLast()) return false;
            head = head.next;
        }
        return true;
    }


        // Function to check if linked list is palindrome
        boolean isPalindrome1( Node head)
        {
            if (head == null  ) return false;
            Node next = head.next;
            if (next == null  ) return true;
            int length = 2;
            while (next.next != null) {
                length++;
                next = next.next;
            }
            int middle = length/2;
            int counter = 1;
            Node middleNode = head;
            while (counter < middle){
                middleNode = head.next;
                middle++;
            }
            if (length%2 == 1) {
                middleNode = middleNode.next;
                middleNode = middleNode.next;
            }

            Node reversedNode =  reverse(middleNode);

            while (reversedNode.next != null){
                if (reversedNode.data != head.data) return false;
                reversedNode = reversedNode.next;
                head = head.next;
            }
            return true;
        }

        public Node reverse(Node head) {
            Node previous = head;
            if (head.next == null) return head;
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

    }
