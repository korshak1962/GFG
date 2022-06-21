package com.slava.tree;

import org.junit.Test;

import java.util.ArrayList;

public class LeftViewBinaryTree {

    @Test
    public void test() {
        Node root = new Node(1);
        root.left = new Node(3);
        root.right = new Node(2);
        root.right.right = new Node(4);
        System.out.println(leftView(root));
    }


    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    ArrayList<Integer> forReturn = new ArrayList<>();

    ArrayList<Integer> leftView(Node root) {
        if (root == null) return forReturn;
        forReturn.add(root.data);
        checkLeftView(root, 2);
        return forReturn;
    }

    private void checkLeftView(Node root, int deep) {
        if (root.left != null) {
            if (deep > forReturn.size()) forReturn.add(root.left.data);
            checkLeftView(root.left, deep + 1);
        }
        if (root.right != null) {
            if (deep > forReturn.size()) forReturn.add(root.right.data);
            checkLeftView(root.right, deep + 1);
        }

    }


}
