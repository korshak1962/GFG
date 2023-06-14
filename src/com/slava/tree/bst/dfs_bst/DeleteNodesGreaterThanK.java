package com.slava.tree.bst.dfs_bst;

import static com.slava.tree.bst.dfs_bst.CheckIsBST.Node;


//https://practice.geeksforgeeks.org/problems/delete-nodes-greater-than-k/1
public class DeleteNodesGreaterThanK {

    public Node deleteNode(Node root, int k) {
        while (root != null && root.data >= k) {
            root = root.left;
        }
        if (root == null) return root;
        Node parent = root;
        while (parent.right != null) {
            if (parent.right.data < k) {
                parent = parent.right;
            } else {
                deleteRight(parent);
            }
        }
        return root;
    }

    private void deleteRight(final Node parent) {
        Node leftOrphan = parent.right.left;
        if (parent.right.right != null) parent.right = parent.right.right;
        else {
            parent.right = leftOrphan;
            return;
        }
        if (leftOrphan == null) return;
        Node currentParent = parent.right;
        while (currentParent.left != null) {
            currentParent = currentParent.left;
        }
        currentParent.left = leftOrphan;
    }
}
