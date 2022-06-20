package com.slava.amazon;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class hierachyTree {

    @Test
    public void test() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(null, "p1"));
        nodes.add(new Node("p1", "ch1"));
        nodes.add(new Node("ch1", "ch11"));
        nodes.add(new Node("ch1", "ch12"));
        nodes.add(new Node("ch11", "ch112"));

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
        printHierachyR(parentToChilds.get(PARENT));
    }

    private void printHierachyR(List<String> childs) {
        if (childs == null) {
            System.out.println("===");  // print full stack instead
            return;
        }
        for (String parent : childs) {
            System.out.println(parent);
            printHierachyR(parentToChilds.get(parent));
        }
    }
}