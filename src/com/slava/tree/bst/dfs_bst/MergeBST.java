package com.slava.tree.bst.dfs_bst;

import java.util.*;

import com.slava.tree.Utility;
import com.slava.tree.Utility.Node;
import org.junit.Test;

//https://practice.geeksforgeeks.org/problems/merge-two-bst-s/1?company%5B%5D=Google&company%5B%5D=Google&page=1&query=company%5B%5DGooglepage1company%5B%5DGoogle
public class MergeBST {

    @Test
    public void test1() {
        String tree = "2 1 3 ";
        Node root1 = Utility.buildTree(tree);
        tree = "1 0 7 ";
        Node root2 = Utility.buildTree(tree);
        List<Integer> merged = merge(root1, root2);
        System.out.println(merged);
    }

    public List<Integer> merge(Node root1, Node root2) {
        List<Integer> result = new ArrayList<>();
        Deque<Node> stack1 = new LinkedList<>();
        Deque<Node> stack2 = new LinkedList<>();
        fillStack(stack1, root1);
        fillStack(stack2, root2);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (stack2.isEmpty() || (!stack1.isEmpty() && stack1.peekLast().data <= stack2.peekLast().data)) {
                Node node = stack1.removeLast();
                result.add(node.data);
                fillStack(stack1, node.right);
            } else {
                Node node = stack2.removeLast();
                result.add(node.data);
                fillStack(stack2, node.right);
            }
        }
        return result;
    }

    private void fillStack(Queue<Node> stack, Node root) {
        while (root != null) {
            stack.add(root);
            root = root.left;
        }
    }
}
