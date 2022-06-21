package com.slava.tree;

import com.slava.tree.bst.dfs_bst.CorrectBST;
import org.junit.Assert;
import org.junit.Test;

public class SumBetween2Leaf {

    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    @Test
    public void test() {
        Node root = new Node(93);
        root.left = new Node(61);
        root.right = new Node(87);
        root.left.left = new Node(9);
        maxPathSum(root);
        System.out.println(toReturn);
        Assert.assertEquals(250, toReturn);
    }
//-9 6 -10

    @Test
    public void test1() {
        Node root = new Node(-9);
        root.left = new Node(6);
        root.right = new Node(-10);
        maxPathSum(root);
        Assert.assertEquals(-13, toReturn);
    }

    //      -10 -1 0 3

    @Test
    public void test2() {
        Node root = new Node(-10);
        root.left = new Node(-1);
        root.right = new Node(0);
        root.left.left = new Node(3);
        maxPathSum(root);
        Assert.assertEquals(-8, toReturn);
    }

    static int toReturn;

    int maxPathSum(Node root) {
        toReturn = Integer.MIN_VALUE;
        if (root == null) return 0;
        maxPathToLeaf(root);
        return toReturn;
    }

    Integer maxPathToLeaf(Node root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root.data;
        Integer maxLeft = maxPathToLeaf(root.left);
        Integer maxRight = maxPathToLeaf(root.right);
        if (maxRight == null) return maxLeft + root.data;
        if (maxLeft == null) return maxRight + root.data;
        toReturn = Integer.max(toReturn, maxLeft + maxRight + root.data);
        return Integer.max(maxRight + root.data, maxLeft + root.data);
    }

}

