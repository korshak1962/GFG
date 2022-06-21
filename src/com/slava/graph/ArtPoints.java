package com.slava.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArtPoints {

    @Test
    public void test() {
        Graph graph = new Graph(6);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.findArtPoints();
        final Set<Graph.Vertex> artPoints = graph.artPoints;
        System.out.println(artPoints);
        Set<Integer> actual = new HashSet<>();
        for (Graph.Vertex vertex : artPoints) {
            actual.add(vertex.id);
        }
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(4);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAone() {
        Graph graph = new Graph(7);
        // edges = [[0,1], [0, 2], [1, 3], [2, 3], [2, 5], [5, 6], [3,4]]
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 5);
        graph.addEdge(5, 6);
        graph.addEdge(3, 4);
        graph.findArtPoints();
        System.out.println(graph.artPoints);
        final Set<Graph.Vertex> artPoints = graph.artPoints;
        System.out.println(artPoints);
        Set<Integer> actual = new HashSet<>();
        for (Graph.Vertex vertex : artPoints) {
            actual.add(vertex.id);
        }
        Set<Integer> expected = new HashSet<>();
        expected.add(2);
        expected.add(3);
        expected.add(5);
        Assert.assertEquals(expected, actual);
    }

    static class Graph {

        List<Vertex> vertices;
        Set<Vertex> artPoints = new HashSet<>();

        public static class Vertex {
            int id;
            List<Vertex> adjustments = new ArrayList<>();
            int discoveryTime = Integer.MAX_VALUE;
            int lowTime = Integer.MAX_VALUE;
            boolean visited = false;

            Vertex(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return " " + id;
            }
        }

        public Graph(int qntyVertices) {
            vertices = new ArrayList<>(qntyVertices);
            for (int i = 0; i < qntyVertices; i++) {
                vertices.add(new Vertex(i));
            }
        }

        public void addEdge(int i, int j) {
            vertices.get(i).adjustments.add(vertices.get(j));
            vertices.get(j).adjustments.add(vertices.get(i));
        }

        int discoveryTime = 0;

        void findArtPoints() {
            Vertex root = vertices.get(0);
            root.discoveryTime = discoveryTime++;
            root.lowTime = root.discoveryTime;
            makeDfs(root, null);
        }

        int makeDfs(Vertex root, Vertex parent) {
            root.visited = true;
            for (Vertex child : root.adjustments) {
                if (child.equals(parent)) {
                    continue;
                }
                int numUnvisitedChilds = 0;
                if (!child.visited) {
                    numUnvisitedChilds++;
                    child.discoveryTime = discoveryTime++;
                    child.lowTime = Integer.min(child.discoveryTime, makeDfs(child, root));
                    if (numUnvisitedChilds > 1 && parent == null) {  // handle the root
                        artPoints.add(root);
                    }
                }
                root.lowTime = Integer.min(child.lowTime, root.lowTime);
                if (parent != null && root.lowTime != Integer.MAX_VALUE && root.discoveryTime <= child.lowTime) {
                    artPoints.add(root);
                }
            }
            return root.lowTime;
        }

    }

}

