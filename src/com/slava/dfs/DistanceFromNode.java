package com.slava.dfs;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//https:practice.geeksforgeeks.org/problems/nodes-at-given-distance-in-binary-tree/1?problemStatus=unsolved&problemType=functional&difficulty%5B%5D=2&page=1&sortBy=submissions&query=problemStatusunsolvedproblemTypefunctionaldifficulty%5B%5D2page1sortBysubmissions
public class DistanceFromNode {
    static LinkedList<Node> deque = new LinkedList<>();
    static List<Integer> result = new ArrayList<>();

    class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static ArrayList<Integer> kDistanceNodes(Node root, int target, int k) {
        return null;
    }

    private static boolean findTarget(Node root, int target, int k) {
        deque.addFirst(root);
        if (deque.size() > k + 1) {
            deque.removeLast();
        }
        if (target == root.data) {
            return true;
        }
        if (root.left != null) {
            if (findTarget(root.left, target, k)) {
                return true;
            }
        }
        if (root.right != null) {
            if (findTarget(root.right, target, k)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testFind() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        findTarget(root, 7, 1);
        Assert.assertTrue("fail", deque.peekFirst().data == 7);
    }

    private static void fillResult(int k) {
        Node child = null;
        int i =0;
        for (Node node:deque) {

            findAllAncestorsInKDistance(node, k-i);
        }
    }

    static private void findAllAncestorsInKDistance(Node root, int k) {
        if (k == 0) {
            result.add(root.data);
            return;
        }
        if (root.left != null) {
            findAllAncestorsInKDistance(root.left, k - 1);
        }
        if (root.right != null) {
            findAllAncestorsInKDistance(root.right, k - 1);
        }
    }
}
