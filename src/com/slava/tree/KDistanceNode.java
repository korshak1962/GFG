package com.slava.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class KDistanceNode {
    static Deque<Node> parents = new LinkedList<>();
    static int target;
    static int k;


    public static ArrayList<Integer> KDistanceNodes(Node root, int target, int k) {
        KDistanceNode.target = target;
        KDistanceNode.k = k;
        ArrayList<Integer> result = new ArrayList<>();

        return result;

    }


    boolean dfs(Node node) {
        if (node.data == target
                || (node.left != null && dfs(node.left))
                || (node.right != null && dfs(node.right))) {
            if (parents.size() < k) parents.add(node);
            return true;
        }
        return false;
    }
}
