package com.slava;

//      https://practice.geeksforgeeks.org/problems/maximum-difference-between-node-and-its-ancestor/1

import java.util.*;

public class TestClassTree<T> {


    public static void main(String[] args) {
      /*  Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            int value = sc.nextInt();
            // printMedian(value);
        }
        sc.close();
        */
        //new TestClassTree(5, -5).findMin();
        TestClassTree ts = new TestClassTree(5);
        ts.showType();
        ts = new TestClassTree("asd");
        ts.showType();
    }


    private T type;

    public TestClassTree(T type) {
        this.type = type;
    }

    public void showType() {
        System.out.println(type.getClass().getSimpleName());
        System.out.println(type);
    }
/*
    private int a;
    private int b;

    public TestClassTree(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void findMin() {
        int min = a + b;
        min = getMin(min, a - b);
        //min = getMin(min, b - a);
        min = getMin(min, b * a);
       // if (a != 0)
       //     min = getMin(min, b / a);
        if (b != 0)
            min = getMin(min, a / b);
        System.out.println(min);
    }

    private int getMin(int min, int newMin) {
        min = (newMin < min) ? newMin : min;
        return min;
    }
*/

    static class Node {
        int data;
        Node left, right, next;

        Node(int d) {
            data = d;
            left = right = next = null;
        }

        @Override
        public String toString() {
            return " " + data;
        }
    }


    //=============================
    static int result;

    /*
    int maxDiffInt(Node root)
    {
        if (root.left!=null){
            result =Integer.max(result,root.data-root.left.data);
            result =Integer.max(result,maxDiffInt(root.left));
        }
        if (root.right!=null){
            result =Integer.max(result,root.data-root.right.data);
            result =Integer.max(result,maxDiffInt(root.right));
        }
        return result;
    }
*/

    public void printTree(Node root) {
        List<Node> parents = new LinkedList();
        List<Node> childs = new LinkedList();
        StringBuilder strChild = new StringBuilder();
        parents.add(root);
        while (!parents.isEmpty()) {
            for (Node parent : parents) {
                if (parent.left != null) {
                    childs.add(parent.left);
                }
                if (parent.right != null) {
                    childs.add(parent.right);
                }
            }
            System.out.println(parents);
            parents.clear();
            parents.addAll(childs);
            childs.clear();
        }
    }

    Node stringToTree(String str) {
        final String[] nodes = str.split("\\s+");
        Node root = new TestClassTree.Node(Integer.valueOf(nodes[0]));
        //3 5 L 3 0 R  5 6 L 5 4 R
        List<Node> freeNodes = new LinkedList<>();
        freeNodes.add(root);
        for (int i = 0; i * 3 < nodes.length - 1; i++) {
            if (nodes[3 * i + 2].equalsIgnoreCase("R")) {
                for (Node freeNode : freeNodes) {
                    if (freeNode.data == Integer.valueOf(nodes[3 * i]) && freeNode.right == null) {
                        freeNode.right = new Node(Integer.valueOf(nodes[3 * i + 1]));
                        freeNodes.add(freeNode.right);
                        if (freeNode.left != null) {
                            freeNodes.remove(freeNode);
                        }
                        break;
                    }
                }
            }
            if (nodes[3 * i + 2].equalsIgnoreCase("L")) {
                for (Node freeNode : freeNodes) {
                    if (freeNode.data == Integer.valueOf(nodes[3 * i]) && freeNode.left == null) {
                        freeNode.left = new Node(Integer.valueOf(nodes[3 * i + 1]));
                        freeNodes.add(freeNode.left);
                        if (freeNode.right != null) {
                            freeNodes.remove(freeNode);
                        }
                        break;
                    }
                }
            }
        }
        return root;
    }


    public int maxDiff(Node root) {
        result = Integer.MIN_VALUE + root.data;
        findMinChild(root);
        return result;
    }

    public int findMinChild(Node root) {
        int minChildLeft = Integer.MAX_VALUE, minChildRight = Integer.MAX_VALUE;

        if (root.left != null) {
            minChildLeft = Integer.min(findMinChild(root.left), root.left.data);
        }
        if (root.right != null) {
            minChildRight = Integer.min(findMinChild(root.right), root.right.data);
        }
        if (root.left == null && root.right == null) {
            return Integer.MAX_VALUE;
        }
        int minChild = Integer.min(minChildLeft, minChildRight);
        result = Integer.max(result, root.data - minChild);
        // System.out.println("result=" + result + " root.data=" + root.data + " minChild=" + minChild);
        return minChild;
    }

    public int findMinChildNonrecursion(Node root) {
        int minChildLeft = Integer.MAX_VALUE, minChildRight = Integer.MAX_VALUE;
        while ((root.left == null && root.right == null)) {
            if (root.right != null) {
                minChildRight = Integer.min(findMinChild(root.right), root.right.data);
            }
        }
        return 0;
    }
    //==============================


    {
        result = -1000000;
    }

    public static int findMaxforKey(Node node, final int val, int size) {
        //  System.out.println("node.data="+node.data+" val="+val);
        if (node.data == val) {
            result = val;
            return result;
        }
        if ((node.data < val) && (val - node.data) < (val - result)) {
            result = node.data;
        }
        if ((node.data < val) && (node.right != null)) {
            //     System.out.println("node.right="+node.right.data);
            findMaxforKey(node.right, val, size);
        }
        if ((node.data >= val) && (node.left != null)) {
            //      System.out.println("node.left="+node.left.data);
            findMaxforKey(node.left, val, size);
        }
        return result;
    }


    class GfG {

        int maxDiam = 1;

        public int diameter(Node root) {
            int dLeft = 1;
            int dRight = 1;
            if (root.left != null) {
                dLeft = getMaxDepth(root.left, 1);
            }
            if (root.right != null) {
                dRight = getMaxDepth(root.right, 1);
            }
            maxDiam = Math.max(maxDiam, dLeft + dRight);
            return maxDiam;
        }


        private int getMaxDepth(Node root, int level) {
            int depthLeft = level;
            int depthRight = level;
            if (root.left != null) {
                depthLeft = getMaxDepth(root.left, level + 1);
            }
            if (root.right != null) {
                depthRight = getMaxDepth(root.right, level + 1);
            }
            maxDiam = Math.max(maxDiam, depthLeft + depthRight);
            return Math.max(depthLeft, depthRight);
        }

        //===========================================

        Set<Integer> depths = new HashSet<>();

        public boolean isBalanced(Node root) {
            getDepth(root, 0);
            int maxDepth = depths.stream().mapToInt(v -> v).max().getAsInt();
            int minDepth = depths.stream().mapToInt(v -> v).min().getAsInt();
            System.out.println("depths=" + depths);
            return (maxDepth - minDepth < 2);

        }

        private void getDepth(Node node, int level) {
            System.out.println("===level=" + level + " node.data=" + node.data);
            if (node.left != null) {
                System.out.println("node.left=" + node.left.data + " level=" + level);
                System.out.println("depths=" + depths);
                getDepth(node.left, level + 1);
            } else {
                System.out.println("node.right=null" + "  level to add=" + level);
                depths.add(level);
            }
            if (node.right != null) {
                System.out.println("node.right=" + node.right.data + " level=" + level);
                System.out.println("depths=" + depths);
                getDepth(node.right, level + 1);
            } else {
                System.out.println("node.right=null" + " level to add=" + level);
                depths.add(level);
            }
        }
    }

    public static void populateNext(Node root) {
        links(root);
    }

    static Node links(Node root) {
        if (root.left != null) {
            links(root.left).next = root;
            return root;
        }
        if (root.right != null) {
            root.next = links(root.right);
            while (root.next != null) {
                root = root.next;
            }
        }
        return root;
    }

    public static void populateNextOld(Node root) {
        List<Node> nodes = linksOld(root);
        Iterator<Node> iter = nodes.iterator();
        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).next = nodes.get(i + 1);
        }
    }

    static List<Node> linksOld(Node root) {
        List<Node> nodes = new LinkedList();
        if (root.left != null) {
            nodes.addAll(linksOld(root.left));
        }
        nodes.add(root);
        if (root.right != null) {
            nodes.addAll(linksOld(root.right));
        }
        return nodes;
    }

}


