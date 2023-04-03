package com.slava.tree;

import org.junit.Assert;
import org.junit.Test;

import com.slava.tree.Utility.Node;

import java.util.Deque;
import java.util.LinkedList;
//https://practice.geeksforgeeks.org/problems/burning-tree/1?page=2&status%5B%5D=unsolved&company%5B%5D=Amazon&category%5B%5D=Tree&curated%5B%5D=6&sortBy=submissions
public class BurningTree {

    static Deque<Node> deque = new LinkedList<>();

    public static int minTime(Node root, int target) {
        dfs(root, target);
        int result = 0;
        int queueNum = 0;
        Node startNode = deque.removeLast();
        result = Integer.max(findLongestPath(startNode, -1) , result);
        while (!deque.isEmpty()) {
            queueNum++;
            final Node upperNode = deque.removeLast();
            if (upperNode.left != startNode) {
                result = Integer.max(findLongestPath(upperNode.left, 0) + queueNum, result);
            } else {
                result = Integer.max(findLongestPath(upperNode.right, 0) + queueNum, result);
            }
            startNode = upperNode;
        }
        return result;
    }

    static int findLongestPath(Node root, int length) {
        if (root == null) return length;
        length++;
        int left = findLongestPath(root.left, length);
        int right = findLongestPath(root.right, length);
        return Integer.max(left, right);
    }

    static boolean dfs(Node root, int target) {
        if (root == null) return false;
        deque.add(root);
        if (root.data == target) {
            return true;
        }
        if (dfs(root.left, target) || dfs(root.right, target)) {
            return true;
        } else deque.removeLast();
        return false;
    }

    @Test
    public void test1() {
        String tree = "1 2 3 4 5 N 6 N N 7 8 N 9 10 11 N N N 12 N N N 13";
        Node root = Utility.buildTree(tree);
        int target = 8;
        dfs(root, target);
        Assert.assertEquals(4, deque.size());
        deque.clear();
        int res = minTime(root, target);
        Assert.assertEquals(7, res);
    }

    @Test
    public void test2() {
        String tree = "1 2 3 4 5 N 6 N N 7 8 N 9 N N N N N 10";
        Node root = Utility.buildTree(tree);
        int target = 8;
        dfs(root, target);
        Assert.assertEquals(4, deque.size());
        deque.clear();
        int res = minTime(root, target);
        Assert.assertEquals(7, res);
    }
/*   1 2 3 4 5 N 6 N N 7 8 N 9 N N N N N 10

                              1
                             / \
                            2   3
                           / \   \
                          4   5   6
                             / \   \
                            7   8   9
                                     \
                                     10



 */

    @Test
    public void test3() {
        String tree = "1 2 3 4 5 N 6 N N 7 8 N 9 N N N N N 10";
        Node root = Utility.buildTree(tree);
        int target = 1;
        dfs(root, target);
        Assert.assertEquals(1, deque.size());
        deque.clear();
        int res = minTime(root, target);
        Assert.assertEquals(4, res);
    }

    @Test
    public void testFindLongestPath(){
        String tree = "1 2 3 ";
        Node root = Utility.buildTree(tree);
        int res = findLongestPath(root,-1);
        Assert.assertEquals(1, res);
    }

}


