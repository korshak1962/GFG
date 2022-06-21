package com.slava;

//https://leetcode.com/problems/minimum-depth-of-binary-tree/
public class minimumDepthBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left != null && root.right != null)
            return Integer.min(minDepth(root.left), minDepth(root.right)) + 1;
        if (root.left == null) return minDepth(root.right) + 1;
        else return minDepth(root.left) + 1;
    }

    int findDepths(TreeNode root) {
        if (root.left == null && root.right == null) return 1;
        return Integer.min(findDepths(root.left), findDepths(root.right)) + 1;
    }

}
