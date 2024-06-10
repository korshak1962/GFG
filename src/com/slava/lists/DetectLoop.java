package com.slava.lists;

import org.junit.Assert;
import org.junit.Test;

//https://www.geeksforgeeks.org/problems/detect-loop-in-linked-list/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class DetectLoop {
    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public static boolean detectLoop(Node head) {
        Node slowerPointer = head;
        Node fastPointer = head.next;
        while (fastPointer != null && slowerPointer != fastPointer) {
            if (fastPointer.next == null) return false;
            fastPointer = fastPointer.next.next;
            slowerPointer = slowerPointer.next;
        }
        if (fastPointer != null) {
            return true;
        }
        return false;
    }

    @Test
    public void testLoop() {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = head;
        Assert.assertTrue(detectLoop(head));
    }

    @Test
    public void testNotLoop() {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        Assert.assertFalse(detectLoop(head));
    }
}
