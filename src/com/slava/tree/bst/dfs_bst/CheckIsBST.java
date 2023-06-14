package com.slava.tree.bst.dfs_bst;

import org.junit.Assert;
import org.junit.Test;

//https://practice.geeksforgeeks.org/problems/check-for-bst/1/?problemType=functional&page=1&sortBy=submissions&query=problemTypefunctionalpage1sortBysubmissions
public class CheckIsBST {
 public static  class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    @Test
    public void testTrue() {
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
        Assert.assertEquals(true, isBST(root));
    }

    @Test
    public void test1() {
        Node root = new Node(2);
        root.right = new Node(7);
        root.right.right = new Node(6);
        root.right.right.right = new Node(5);
        root.right.right.right.right = new Node(9);
        root.right.right.right.left = new Node(5);
        Assert.assertEquals(false, isBST(root));
    }


    @Test
    public void test3() {
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(2);
        root.left.left = new Node(2);
        Assert.assertEquals(false, isBST(root));
    }

    @Test
    public void test2() {
        Node root = new Node(12);
        root.left = new Node(4);
        Assert.assertEquals(true, isBST(root));
    }

    boolean isBST(Node root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean isBST(Node root, int min, int max) {
        if (root == null) return true;
        if (root.data > max || root.data < min) return false;
        boolean left = true;
        boolean right = true;
        if (root.left != null) {
            left = isBST(root.left, min, Integer.min(root.data, max) - 1);
        }
        if (root.right != null) {
            right = isBST(root.right, Integer.max(root.data, min) + 1, max);
        }
        return left && right;
    }

}
