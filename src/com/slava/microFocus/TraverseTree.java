package com.slava.microFocus;

import java.util.LinkedList;
import java.util.Queue;

public class TraverseTree {

    public void traverseInOrder(Node node) {
        System.out.println(node.value); // preOrder
        if (node.leftChild != null) {
            traverseInOrder(node.leftChild);
        }

        if (node.rightChild != null) {
            traverseInOrder(node.rightChild);
        }

    }

    
    public void traverseBFS(Node node) {
        Queue<Node> currentRow = new LinkedList<>();
        currentRow.add(node);
        while (!currentRow.isEmpty()) {
            if (node.leftChild != null) {
                currentRow.add(node.leftChild);
            }
            if (node.rightChild != null) {
                currentRow.add(node.rightChild);
            }
        }
    }
}
