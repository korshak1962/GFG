package com.slava.lists;

import org.junit.Test;

// https://www.geeksforgeeks.org/problems/add-two-numbers-represented-by-linked-lists/1?page=1&category=Linked%20List&company=Amazon&difficulty=Basic,Medium,Hard&sortBy=submissions

public class SumOfTwoLists {


    static Node addTwoLists(Node num1, Node num2) {
        Node head1 = reversList(num1,true);
        Node head2 = reversList(num2,true);
        int resedual = 0;
        Node resultHead = null;
        Node current = null;
        Node result;
        int sum = 0;
        while (head1 != null && head2 != null) {
            sum = head1.data + head2.data + resedual;
            resedual = 0;
            if (sum > 9) {
                resedual = 1;
                sum = sum - 10;
            }
            result = new Node(sum);
            if (resultHead == null) {
                resultHead = result;
                current = result;
            } else{
            current.next = result;
            current = current.next;
            }
            head1 = head1.next;
            head2 = head2.next;
        }

        if (head2 == null && head1 == null && resedual != 0) {
            current.next = new Node(resedual);
        }

        while (head1 == null && head2 != null) {
            sum = head2.data + resedual;
            resedual = 0;
            if (sum > 9) {
                resedual = 1;
                sum = sum - 10;
            }
            result = new Node(sum);
            current.next = result;
            current = current.next;
            head2 = head2.next;
            if (resedual==0){
            current.next = head2;
            break;
            }
        }
        while (head1 != null && head2 == null) {
            sum = head1.data + resedual;
            resedual = 0;
            if (sum > 9) {
                resedual = 1;
                sum = sum - 10;
            }
            result = new Node(sum);
            current.next = result;
            current = current.next;
            head1 = head1.next;
            if (resedual==0){
                current.next = head1;
                break;
            }
        }

        if (head2 == null && head1 == null && resedual != 0) {
            current.next = new Node(resedual);
        }

        return reversList(resultHead,false);
    }


    static Node reversList(Node head, boolean deleteZeros) {
        if (head == null) return null;
        while (deleteZeros && head.data == 0 && head.next != null) {
            head = head.next;
        }
        Node prev = head;
        Node next = head.next;
        prev.next = null;
        Node nextNext = null;

        while (next != null) {
            nextNext = next.next;
            next.next = prev;
            prev = next;
            next = nextNext;
        }

        return prev;
    }


    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    @Test
    public void test() {
        Node head1 = new Node(1);
        head1.next = new Node(5);
        Node head2 = new Node(2);

        Node result = addTwoLists(head1, head2);
        while (result != null) {
            System.out.print(result.data +",");
            result = result.next;
        }
    }

    @Test
    public void test1() {
        Node head1 = new Node(1);
        head1.next = new Node(5);
        head1.next.next = new Node(8);
        Node head2 = new Node(2);

        Node result = addTwoLists(head1, head2);

        while (result != null) {
            System.out.print(result.data +",");
            result = result.next;
        }
    }
    /*
    8 7 9 9 1 0 4 7
    0 8 9 1 3 0 0 4
    Your Code's output is:
            1 9 6 9 0 4 0 5 1
    It's Correct output is:
            9 6 9 0 4 0 5 1

     */

    @Test
    public void test2() {
        //8 7 9 9 1 0 4 7
        Node head1 = new Node(8);
        head1.next = new Node(7);
        head1.next.next = new Node(9);
        head1.next.next.next = new Node(9);
        head1.next.next.next.next = new Node(1);
        head1.next.next.next.next.next = new Node(0);
        head1.next.next.next.next.next.next = new Node(4);
        head1.next.next.next.next.next.next.next = new Node(7);

        //0 8 9 1 3 0 0 4
        Node head2 = new Node(0);
        head2.next = new Node(8);
        head2.next.next = new Node(9);
        head2.next.next.next = new Node(1);
        head2.next.next.next.next = new Node(3);
        head2.next.next.next.next.next = new Node(0);
        head2.next.next.next.next.next.next = new Node(0);
        head2.next.next.next.next.next.next.next = new Node(4);

        Node result = addTwoLists(head1, head2);

        while (result != null) {
            System.out.print(result.data +",");
            result = result.next;
        }
    }


}
