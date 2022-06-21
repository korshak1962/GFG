package com.slava.bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

//https://practice.geeksforgeeks.org/problems/d1afdbd3d49e4e7e6f9d738606cd592f63e6b0f0/1/?page=1&difficulty[]=2&category[]=Tree&sortBy=submissions
public class CovidKit {


    @Test
    // 1 2 3 N N N 4 N 5 N 6
    public void test() {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.right.right = new Node(4);
        node.right.right.right = new Node(5);
        node.right.right.right.right = new Node(6);
        int res = supplyVaccine(node);
        Assert.assertEquals(2, res);
    }

    @Test
    //1 N 2 N 3 N 4
    public void test1() {
        Node node = new Node(1);
        node.right = new Node(2);
        node.right.right = new Node(3);
        node.right.right.right = new Node(4);
        int res = supplyVaccine(node);
        Assert.assertEquals(2, res);
    }

    @Test
    //1 2 3 4 5 6 7 8
    public void test2() {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right.left = new Node(6);
        node.right.right = new Node(7);
        node.left.left.left = new Node(8);
        int res = supplyVaccine(node);
        Assert.assertEquals(3, res);
    }

    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    public static int supplyVaccine(Node root) {
        LinkedList<Integer> layersSizes = new LinkedList<>();
        List<Integer> numOfOrphans = new ArrayList<>();
        List<Node> layer1 = new ArrayList<>();
        List<Node> layer2 = new ArrayList<>();
        layer1.add(root);
        layersSizes.add(layer1.size());
        while (!layer1.isEmpty()) {
            getNextLayer(layer1, layer2, numOfOrphans);
            layer1.clear();
            layer1.addAll(layer2);
            layersSizes.add(layer2.size());
            layer2.clear();
        }
        layersSizes.removeLast();
        int dp[] = new int[layersSizes.size()];
        if (layersSizes.size() <= 2) return 1;
        dp[0] = 1;
        dp[1] = layersSizes.get(1);
        dp[2] = dp[0] + layersSizes.get(2);
        int i = 3;
        while (i < layersSizes.size()) {
            dp[i] = layersSizes.get(i) + dp[i - 2]; //by 2
            dp[i] = Integer.min(dp[i], //by 2
                    layersSizes.get(i) + dp[i - 3] + numOfOrphans.get(i - 1)); //by 3
            i++;
        }
        return Integer.min(dp[i - 2], dp[i - 1]);
    }

    private static void getNextLayer(List<Node> layer1, List<Node> layer2, List<Integer> numOfOrphans) {
        int orphans = 0;
        for (Node node : layer1) {
            if (node.left == null && node.right == null) {
                orphans++;
                continue;
            }
            if (node.left != null) layer2.add(node.left);
            if (node.right != null) layer2.add(node.right);
        }
        numOfOrphans.add(orphans);
    }
}
