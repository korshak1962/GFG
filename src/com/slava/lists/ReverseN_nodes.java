package com.slava.lists;

import org.junit.Test;

public class ReverseN_nodes {

    void reverseInterval(NodeOneWay head, int nStart, int nEnd) {  // 1234,2,3

        
        int counter = 1;
        NodeOneWay current = head;  // 1
        while (current.nextNode != null && counter <= nStart + 1) {
            current = current.nextNode; //2
            counter++;   //2
        }
        current.nextNode = reverseNfirstNodes(current.nextNode, nEnd - nStart); // 2,1
    }

    NodeOneWay reverseNfirstNodes(NodeOneWay head, int numToReverse) {  //2,1
        if (head == null) return head;
        NodeOneWay current = head; // 2
        NodeOneWay next = current.nextNode;
        NodeOneWay nextNext = null;
        int counter = 0;
        while (current.nextNode != null && counter < numToReverse) {
            nextNext = next.nextNode; //4
            next.nextNode = current; // 3->2
            current = next; // 3
            next = nextNext;
            counter++; //1
        }
        if (nextNext != null) {
            head.nextNode = nextNext;
        }
        return current;
    }

    @Test
    public void simple1234() {
        NodeOneWay n1 = new NodeOneWay<>(1);
        NodeOneWay n2 = new NodeOneWay<>(2);
        n1.nextNode = n2;
        NodeOneWay n3 = new NodeOneWay<>(3);
        n2.nextNode = n3;
        NodeOneWay n4 = new NodeOneWay<>(4);
        n3.nextNode = n4;

        reverseInterval(n1, 2, 3);
        NodeOneWay current = n1;
        System.out.println(current.item);
        while (current.nextNode != null) {
            current = current.nextNode;
            System.out.println(current.item);
        }
    }


}
