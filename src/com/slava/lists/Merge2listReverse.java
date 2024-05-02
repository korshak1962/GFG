package com.slava.lists;

import org.junit.Test;

//https://www.geeksforgeeks.org/problems/merge-2-sorted-linked-list-in-reverse-order/1
public class Merge2listReverse {
    class Node
    {
        int data;
        Node next;

        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    Node reverseList(Node node){
        if (node == null) return node;
        Node current = node;
        Node next = current.next;
        current.next = null;
        Node nextNext;
        while (next != null){
            nextNext = next.next;
            next.next =current;
            current = next;
            next = nextNext;
        }
        return current;
    }

    @Test
    public void tReverseList(){
        Node n1=new Node(1);
        n1.next=new Node(2);
        n1.next.next=new Node(3);
        n1=reverseList(n1);
        System.out.println(n1);
    }

    Node mergeResult(Node node1, Node node2)
    {
        node1 =	reverseList(node1);
        node2 =	reverseList(node2);
        if (node1==null) return node2;
        if (node2==null) return node1;
        Node result;
        if (node1.data>node2.data) {
            result = node1;
            node1 = node1.next;

        }
        else {
            result = node2;
            node2 = node2.next;
        }
        Node toReturn = result;;
        while (node1 !=null && node2!= null){
            if (node1.data > node2.data) {
                result.next = node1;
                node1 = node1.next;
            }
            else {
                result.next = node2;
                node2 = node2.next;
            }
            result=result.next;
        }

        if (node1 == null){
            result.next = node2;
        }
        else{
            result.next = node1;
        }
        return toReturn;
    }

    @Test
    public void tMergeResult(){
        Node n1=new Node(1);
        n1.next=new Node(2);
        n1.next.next=new Node(3);

        Node n2=new Node(4);
        n2.next=new Node(5);
        n2.next.next=new Node(6);
        n1=mergeResult(n1,n2);
        System.out.println(n1);
    }
}
