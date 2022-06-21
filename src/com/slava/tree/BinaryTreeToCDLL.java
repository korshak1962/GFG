package com.slava.tree;

import org.junit.Test;
//https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1
public class BinaryTreeToCDLL {

    @Test
    public void test1() {
        Node root = new Node(10);
        root.left = new Node(12);
        root.right = new Node(15);
        root.left.left = new Node(25);
        root.left.right = new Node(30);
        root.right.left = new Node(36);
        printList(bToDLL(root));
    }

    void printList(Node root) {
        Node currentNode = root;
        System.out.println("forward: ");
        System.out.print(" " + currentNode.data);
        while (currentNode.right != null)  {
            currentNode = currentNode.right;
             System.out.print(" " + currentNode.data);
        }
        System.out.println("\nbackward: ");
        System.out.print(" " + currentNode.data);
        while (currentNode.left != null)  {
            currentNode = currentNode.left;
            System.out.print(" " + currentNode.data);
        }
    }

    HeadAndTail bTreeToDlist(Node root) {
        if (root == null) {
            return null;
        }
        if (root.right == null && root.left == null) {
            return new HeadAndTail(root, root);
        }
        return merge(bTreeToDlist(root.left), bTreeToDlist(root.right), root);
    }

    HeadAndTail merge(HeadAndTail headAndTailLeft, HeadAndTail headAndTailRight, Node root) {
        Node atMostLeft = root;
        Node atMostRight = root;
        if (headAndTailLeft != null) {
            doubleLink(headAndTailLeft.right, root);
            atMostLeft = headAndTailLeft.left;
        }
        if (headAndTailRight != null) {
            doubleLink(root, headAndTailRight.left);
            atMostRight = headAndTailRight.right;
        }
        return new HeadAndTail(atMostLeft, atMostRight);
    }

    Node bToDLL(Node root) {
        HeadAndTail ht = bTreeToDlist(root);
        return ht.left;
    }

    void doubleLink(Node left, Node right) {
        left.right = right;
        right.left = left;
    }

}

class HeadAndTail {
    Node right;
    Node left;

    public HeadAndTail(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}

class Node {
    int data;
    Node left, right;

    Node(int d) {
        data = d;
        left = right = null;
    }
}
