package com.slava.dp;

import org.junit.Test;

public class HeightOfTree {

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
        Tree tree = new Tree();
        int height = tree.height(new Node(1));
        System.out.println(height);
    }


    class Tree {
        // return the Height of the given Binary Tree
        int height(Node root) {
            if (root == null) {
                return 0;
            }
            return Integer.max(height(root.left), height(root.right)) + 1;
        }

       private boolean isIdentical(Node root1, Node root2) {
            if (root1 == null && root2 == null) {
                return true;
            }
            if (root1 == null || root2 == null) {
                return false;
            }
            if (root1.data != root2.data) {
                return false;
            } else if (!isIdentical(root1.left, root2.left)) {
                return false;
            } else if (!isIdentical(root1.right, root2.right)) {
                return false;
            }
            return true;
        }

        public int LISS(Node node) {
            if (node == null) {
                return 0;
            }
            final int leftGrads = node.left == null ? 0 : LISS(node.left.left) + LISS(node.left.right);
            final int rightGrads = node.right == null ? 0 : LISS(node.right.left) + LISS(node.right.right);
            return Integer.max((1 + leftGrads + rightGrads),
                    LISS(node.left) + LISS(node.right));
        }

    }

}
