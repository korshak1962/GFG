package com.slava.meta.tree;

public class RangeSumBST {
    int result;

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root.val >= low && root.val <= high) {
            result += root.val;
        }
        if (root.left != null && low <= root.val) {
            rangeSumBST(root.left,low,high);
        }
        if (root.right != null && high >= root.val) {
            rangeSumBST(root.right,low,high);
        }
        return result;
    }
    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
