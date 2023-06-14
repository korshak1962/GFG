package com.slava.tree.bst.dfs_bst;

import static com.slava.tree.bst.dfs_bst.CheckIsBST.Node;

//https://practice.geeksforgeeks.org/problems/predecessor-and-successor/1
public class PredecessorAndSuccessor {
    static Node pre = null, suc = null;

    public static void findPreSuc(Node root, int key) {
        if (root == null) return;
        if (root.data == key) {
            findPreSuc(root.left, key);
            findPreSuc(root.right, key);
        }
        if (root.data > key) {
            suc = root;
            findPreSuc(root.left, key);
        }
        if (root.data < key) {
            pre = root;
            findPreSuc(root.right, key);
        }


    /* There are two static nodes defined above pre(representing predecessor)
    and suc(representing successor) as follows:
       static Node pre=null,suc=null
       You need to update these both.
       And the data inside these classes will be printed automatically by the driver code.
    */
    }
}
