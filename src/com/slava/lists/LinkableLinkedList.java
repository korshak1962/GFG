package com.slava.lists;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkableLinkedList<E> {
    public static class ListNode<E> {
        public E item;
        public ListNode<E> nextNode;
        public ListNode<E> prevNode;

        ListNode(ListNode<E> prev, E element, ListNode<E> next) {
            this.item = element;
            this.nextNode = next;
            this.prevNode = prev;
        }
    }

    public LinkableLinkedList(E elem) {
        firstNode = new ListNode(null, elem, null);
        lastNode = firstNode;
    }

    public ListNode<E> firstNode;
    public ListNode<E> lastNode;

    public void add(E elem) {
        final ListNode newLast = new ListNode(lastNode, elem, null);
        lastNode.nextNode = newLast;
        lastNode = newLast;
    }

    public List<E> getList() {
        List<E> lst = new ArrayList();
        ListNode<E> current = firstNode;
        lst.add(current.item);
        while (current.nextNode != null) {
            current = current.nextNode;
            lst.add(current.item);
        }
        return lst;
    }

    public static <E> LinkableLinkedList linkTwoLists(LinkableLinkedList<E> lst1, LinkableLinkedList<E> lst2) {
        if (lst1 == null) return lst2;
        if (lst2 == null) return lst1;
        lst1.lastNode.nextNode = lst2.firstNode;
        lst1.lastNode = lst2.lastNode;
        return lst1;
    }

}
