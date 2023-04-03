package com.slava.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Utility {

    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static Node buildTree(String str) {
        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }
        String[] values = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(values[0]));
        // Push the root to the queue
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        // Starting from the second element
        int i = 1;
        while (queue.size() > 0 && i < values.length) {
            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();
            // Get the current node's value from the string
            String currVal = values[i];
            // If the left child is not null
            if (!currVal.equals("N")) {
                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }
            // For the right child
            i++;
            if (i >= values.length)
                break;
            currVal = values[i];
            // If the right child is not null
            if (!currVal.equals("N")) {
                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }
        return root;
    }

}
