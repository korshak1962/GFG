package com.slava.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

//https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class UndirectedGraphCycle {

    private ArrayList<ArrayList<Integer>> adj;
    private Set<Integer> visited;
  //  private TreeSet<Integer> visitedDebug = new TreeSet<>();

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        this.adj = adj;
        this.visited = new HashSet<>();
        for (int i = 0; i < adj.size(); i++) {
            if (!visited.contains(i)) {
                if (deepFS(-1, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean deepFS(int from, int to) {
      //  visitedDebug.add(to);
        if (!visited.add(to)) {
            return true;
        }
        for (int edge : adj.get(to)) {
            if (edge == from) {
                continue;
            }
            if (deepFS(to, edge)) return true;
        }
        return false;
    }

    @Test
    public void testPositive() {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(List.of(1)));
        adj.add(new ArrayList<>(List.of(0, 2, 4)));
        adj.add(new ArrayList<>(List.of(1, 3)));
        adj.add(new ArrayList<>(List.of(2, 4)));
        adj.add(new ArrayList<>(List.of(1, 3)));
        Assert.assertTrue(isCycle(adj.size(), adj));
    }

    @Test
    public void testNegative() {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        // {{}, {2}, {1, 3}, {2}}
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>(List.of(2)));
        adj.add(new ArrayList<>(List.of(1, 3)));
        adj.add(new ArrayList<>(List.of(2)));
        Assert.assertFalse(isCycle(adj.size(), adj));
    }

    @Test
    public void testPositiveFromFile() {
        ArrayList<ArrayList<Integer>> adj = GraphReader.readGraphToAdjList("graph.txt");
        Assert.assertTrue(isCycle(adj.size(), adj));
    }

    @Test
    public void testPositiveFromFile1() {
        ArrayList<ArrayList<Integer>> adj = GraphReader.readGraphToAdjList("graph1.txt");
        Assert.assertTrue(isCycle(adj.size(), adj));
    }
}
