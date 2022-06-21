package com.slava.tree.bst.dfs_bst;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
//https://www.geeksforgeeks.org/find-k-th-smallest-element-in-bst-order-statistics-in-bst/
// keyword bst dfs
public class KthSmallestElement {


    public static TreeNode insert(TreeNode root, int x)
    {
        if (root == null)
            return new TreeNode(x);
        if (x < root.val)
            root.left = insert(root.left, x);
        else if (x > root.val)
            root.right = insert(root.right, x);
        return root;
    }

    public Integer kthSmallest(TreeNode root, int inputK) {
        KthSmallestElement.k = inputK;
        List<Integer> increasedList = getIncreasedListFromBST(root);
        if (increasedList.size() >= KthSmallestElement.k) return increasedList.get(KthSmallestElement.k - 1);
        return null;
    }

    static int k;

    List<Integer> getIncreasedListFromBST(TreeNode root) {
        //condition of exit
        if (root == null) return new LinkedList<>();
        if (root.left == null && root.right == null) {
            List<Integer> increasedList = new LinkedList<>();
            increasedList.add(root.val);
            return increasedList;
        }
        // traversal in order
        List<Integer> increasedList = getIncreasedListFromBST(root.left);
        increasedList.add(root.val);
        increasedList.addAll(getIncreasedListFromBST(root.right));
        // save memory
        if (increasedList.size() >= k) return increasedList;
        k = k - increasedList.size();
        increasedList.clear();
        return increasedList;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void testSimple() {
        TreeNode root = getTreeNode();
        int res = kthSmallest(root, 2);
        Assert.assertEquals(7, res);
    }

    @Test
    public void testSimple1() {
        TreeNode root = getTreeNode();
        int res = kthSmallest(root, 4);
        Assert.assertEquals(9, res);
    }

    @Test
    public void testSimple2() {
        TreeNode root = getTreeNode();
        int res = kthSmallest(root, 1);
        Assert.assertEquals(1, res);
    }

    //[3,1,4,null,2]

    @Test
    public void testSimple3() {
        TreeNode root = getTreeNodeLeet();
        int res = kthSmallest(root, 1);
        Assert.assertEquals(1, res);
    }

    @Test
    public void testSimple33() {
        TreeNode root = getTreeNodeLeet();
        int res = kthSmallest(root, 3);
        Assert.assertEquals(3, res);
    }

    private TreeNode getTreeNodeLeet() {
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        return root;
    }

    private TreeNode getTreeNode() {
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(12);
        root.right.right = new TreeNode(14);
        root.right.left = new TreeNode(11);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(9);
        root.left.left.left = new TreeNode(1);
        return root;
    }

    @Test
    public void testSimple33GFG() {
        TreeNode root = getTreeNodeLeet();
        int res = kthSmallestGFG(root, 3).val;
        Assert.assertEquals(3, res);
    }

    static int count = 0;
// from gfg
    public static TreeNode kthSmallestGFG(TreeNode root, int k) {
        // base case
        if (root == null)
            return null;
        // search in left subtree
        TreeNode left = kthSmallestGFG(root.left, k);
        // if k'th smallest is found in left subtree, return it
        if (left != null)
            return left;
        // if current element is k'th smallest, return it
        count++;
        if (count == k)
            return root;
        // else search in right subtree
        return kthSmallestGFG(root.right, k);
    }

}
