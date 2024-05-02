package com.slava.lists;

import org.junit.Test;

//https://practice.geeksforgeeks.org/problems/remove-loop-in-linked-list/1/?category[]=two-pointer-algorithm&page=1&query=category[]two-pointer-algorithmpage1
public class RemoveLoopInList {

  public static  class Node {
        int data;
        Node next;

        public  Node(){}
        public Node(int data) {
            this.data = data;
            next = null;
        }
    }

    @Test
    public void test() {
        // 1 -> 3 -> 4->3
        Node first = new Node();
        first.data = 1;
        Node second = new Node();
        first.next = second;
        second.data = 3;
        Node third = new Node();
        second.next = third;
        third.data = 4;
        third.next = second;
        removeLoop(first);
    }


    public static void removeLoop(final Node head) {
        Node cycled = findCycle(head);
        if (cycled == null) {
            return;
        }
        int cycleLength = findLength(cycled);
        fixList(head, cycleLength);

    }

    private static void fixList(Node head, final int cycleLength) {
        Node ahead = head;
        for (int i = cycleLength; i > 0; i--) {
            ahead = ahead.next;
        }
        Node normal = head;
        while (normal != ahead) {
            normal = normal.next;
            ahead = ahead.next;
        }
        for (int i = cycleLength; i > 1; i--) {
            ahead = ahead.next;
        }
        ahead.next = null;
    }

    private static int findLength(Node cycled) {
        int cycleLength = 1;
        Node nextInCycle = cycled.next;
        while (cycled != nextInCycle) {
            nextInCycle = nextInCycle.next;
            cycleLength++;
        }
        return cycleLength;
    }

    private static Node findCycle(final Node head) {
        Node nodePointerFast = head;
        Node nodePointerSlow = head;
        while (nodePointerFast != null && nodePointerFast.next != null) {
            nodePointerSlow = nodePointerSlow.next;
            nodePointerFast = nodePointerFast.next.next;
            if (nodePointerSlow == nodePointerFast) {
                return nodePointerSlow;
            }
        }
        return null;
    }


}
