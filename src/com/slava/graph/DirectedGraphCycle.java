package com.slava.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DirectedGraphCycle {
    private ArrayList<ArrayList<Integer>> adj;
    private Set<Integer> visited;
    private Set<Integer> currentVisit;
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
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

}
