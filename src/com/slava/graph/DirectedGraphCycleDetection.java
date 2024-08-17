package com.slava.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
/*
This implementation uses a depth-first search (DFS) approach to detect cycles in the directed graph. Here's a brief explanation of how it works:

The hasCycle method iterates through all nodes in the graph, calling dfs on each unvisited node.
The dfs method uses two sets:

visited: keeps track of all visited nodes
recursionStack: keeps track of nodes in the current DFS path


If a node is encountered that's already in the recursionStack, a cycle is detected.
 */

public class DirectedGraphCycleDetection {
    public static boolean hasCycle(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();

        for (Integer node : graph.keySet()) {
            if (dfs(node, graph, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private static boolean dfs(Integer node, Map<Integer, List<Integer>> graph,
                               Set<Integer> visited, Set<Integer> recursionStack) {
        if (recursionStack.contains(node)) {
            return true;
        }

        if (visited.contains(node)) {
            return false;
        }

        visited.add(node);
        recursionStack.add(node);

        List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
        for (Integer neighbor : neighbors) {
            if (dfs(neighbor, graph, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack.remove(node);
        return false;
    }

    public static void main(String[] args) {
        // Example usage
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(0, 3));
        graph.put(3, Arrays.asList(3));

        boolean hasCycle = hasCycle(graph);
        System.out.println("Graph has cycle: " + hasCycle);
    }

    @Test
    public void simpleFalse(){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(1, 2));
        graph.put(1, List.of(3, 4));
        graph.put(2, List.of(5, 6));
        graph.put(3, List.of(7));
        graph.put(5, List.of(7));

        Assert.assertFalse( hasCycle(graph));

    }
}