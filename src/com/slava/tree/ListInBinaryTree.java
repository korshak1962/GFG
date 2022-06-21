package com.slava.tree;

import java.util.Deque;
import java.util.LinkedList;

//https://leetcode.com/problems/linked-list-in-binary-tree/
public class ListInBinaryTree {

    public boolean isSubPathMine(ListNode head, TreeNode root) {
        if (root == null) return false;
        if (head == null) return true;
        if (root.val == head.val) {
            if (head.next == null) return true;
            if (isSubPath(head.next, root.left) || isSubPath(head.next, root.right)) return true;
        }
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root == null) return false;
        return DFS(root, head) ? true : (isSubPath(head, root.left) || isSubPath(head, root.right));
    }

    public boolean DFS(TreeNode t, ListNode l) {
        if(t == null || t.val != l.val) return false;
        return l.next == null ? true : DFS(t.left, l.next) || DFS(t.right, l.next);
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class TreeNode {
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

}



