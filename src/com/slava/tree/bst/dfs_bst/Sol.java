package com.slava.tree.bst.dfs_bst;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sol
{


    static Node buildTree(String str) {

        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }

        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while (queue.size() > 0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= ip.length)
                break;

            currVal = ip[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    public static boolean isBST(Node n, int lower, int upper) {
        if (n == null)
            return true;
        if (n.data <= lower || n.data >= upper)
            return false;
        return (isBST(n.left, lower, n.data) && isBST(n.right, n.data, upper));
    }

    @Test
    public void testBoth() {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(20);
        root = correctBST(root);
        Assert.assertEquals(true, isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    //93 61 87 9
    @Test
    public void test2() {
        Node root = new Node(93);
        root.left = new Node(61);
        root.right = new Node(87);
        root.left.left = new Node(9);
        root = correctBST(root);
        System.out.println(root.data);
        Assert.assertEquals(true, isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test3() {
        String str = "2 1 83 N 3 47 96 4 N 6";
        Node root = buildTree(str);
        root = correctBST(root);
        Assert.assertEquals(true, isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }



    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    public Node correctBST(Node root) {
        LinkableLinkedList<Node> linkableLinkedList = getIncreasedListFromBST(root);
        final List<Node> list = linkableLinkedList.getList();
        //System.out.println(list);
        sortListWithTwoWrongs(list);
        //System.out.println(list);
        return root;
    }

    LinkableLinkedList<Node> getIncreasedListFromBST(Node root) {
        //condition of exit
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            LinkableLinkedList<Node> increasedList = new LinkableLinkedList<>(root);
            return increasedList;
        }
        // traversal in order
        LinkableLinkedList<Node> increasedListFromLeft = getIncreasedListFromBST(root.left);
        if (increasedListFromLeft != null) increasedListFromLeft.add(root);
        else increasedListFromLeft = new LinkableLinkedList<>(root);
        final LinkableLinkedList<Node> increasedListFromRight = getIncreasedListFromBST(root.right);
        return LinkableLinkedList.linkTwoLists(increasedListFromLeft, increasedListFromRight);
    }

    public void sortListWithTwoWrongs(List<Node> lst) {
        Node first = null;
        Node last = null;
        for (int i = 0; i < lst.size() - 1; i++) {
            if (lst.get(i + 1).data < lst.get(i).data) {
                first = lst.get(i);
                for (int j = lst.size() - 1; j > i; j--) {
                    if (lst.get(j).data < lst.get(j - 1).data) {
                        last = lst.get(j);
                        swapData(first, last);
                        return;
                    }
                }
            }
        }

    }

    private void swapData(Node node1, Node node2) {
        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    public  static class LinkableLinkedList<E> {
        static class ListNode<E> {
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

}
