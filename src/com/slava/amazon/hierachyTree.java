package com.slava.amazon;

import org.junit.Test;

import java.util.*;

public class hierachyTree {

    @Test
    public void test() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(null, "lev1"));
        nodes.add(new Node("lev1", "lev2"));
        nodes.add(new Node("lev2", "lev3"));
        nodes.add(new Node("lev2", "lev3_1"));
        nodes.add(new Node("lev3", "lev4"));

        nodes.add(new Node(null, "p2"));
        nodes.add(new Node("p2", "ch2"));

        printHierachy(nodes);
    }

    static class Node {
        String parent;
        String child;

        public Node(String parent, String child) {
            this.parent = parent;
            this.child = child;
        }
    }

    private Map<String, List<String>> parentToChilds = new HashMap<>();
    final String PARENT = "PARENT";

    public void printHierachy(List<Node> nodes) {
        for (Node node : nodes) {
            if (node.parent == null) node.parent = PARENT;
            parentToChilds.computeIfPresent(node.parent, (k, v) -> {
                v.add(node.child);
                return v;
            });
            parentToChilds.computeIfAbsent(node.parent, k -> {
                List<String> childs = new ArrayList<>();
                childs.add(node.child);
                return childs;
            });
        }
        List<String> printStack = new LinkedList<>();
        printStack.add(PARENT);
        printHierachyR(parentToChilds.get(PARENT),printStack);
    }

    private void printHierachyR(List<String> childs,List<String> printStack) {
        if (childs == null) {
            System.out.println(printStack);  // print full stack instead
            return;
        }
        for (String parent : childs) {
            printStack.add(parent);
            printHierachyR(parentToChilds.get(parent),printStack);
            printStack.remove(parent);
        }
    }
}