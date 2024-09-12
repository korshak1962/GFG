package com.slava.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
/*
This implementation uses a depth-first search (DFS) approach to detect cycles
 in the directed graph. Here's a brief explanation of how it works:

The hasCycle method iterates through all nodes in the graph, calling dfs on each unvisited node.
The dfs method uses two sets:

visited: keeps track of all visited nodes
recursionStack: keeps track of nodes in the current DFS path


If a node is encountered that's already in the recursionStack, a cycle is detected.
 */

public class DirectedGraphCycleDetection {
    static Map<Integer, List<Integer>> graph;
    static Set<Integer> visited;
    static Set<Integer> recursionStack;
    static Map<Integer, Integer> parent;

    public static List<Integer> findCycle(Map<Integer, List<Integer>> graph) {
        DirectedGraphCycleDetection.graph = graph;
        visited = new HashSet<>();
        recursionStack = new HashSet<>();
        parent = new HashMap<>();

        for (Integer node : graph.keySet()) {
            List<Integer> cycle = dfs(node);
            if (cycle != null) {
                return cycle;
            }
        }

        return new ArrayList<>(); // No cycle found
    }

    private static List<Integer> dfs(Integer node) {
        if (recursionStack.contains(node)) {
            return constructCycle(node, parent);
        }

        if (visited.contains(node)) {
            return null;
        }

        visited.add(node);
        recursionStack.add(node);

        List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
        for (Integer neighbor : neighbors) {
            parent.put(neighbor, node);
            List<Integer> cycle = dfs(neighbor);
            if (cycle != null) {
                return cycle;
            }
        }

        recursionStack.remove(node);
        return null;
    }

    private static List<Integer> constructCycle(Integer start, Map<Integer, Integer> parent) {
        List<Integer> cycle = new ArrayList<>();
        Integer current = start;
        do {
            cycle.add(current);
            current = parent.get(current);
        } while (!current.equals(start));
        cycle.add(start);
        Collections.reverse(cycle);
        return cycle;
    }

    @Test
    public void simpleTrue() {
        // Example usage
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(0, 3));
        graph.put(3, Arrays.asList(3));

        List<Integer> cycle = findCycle(graph);
        Assert.assertFalse(cycle.isEmpty());
        System.out.println("Graph has cycle: " + cycle);
    }

    @Test
    public void simpleFalse() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(1, 2));
        graph.put(1, List.of(3, 4));
        graph.put(2, List.of(5, 6));
        graph.put(3, List.of(7));
        graph.put(5, List.of(7));

        List<Integer> cycle = findCycle(graph);
        Assert.assertTrue(cycle.isEmpty());

    }
}