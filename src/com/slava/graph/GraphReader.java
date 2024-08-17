package com.slava.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GraphReader {
    public static ArrayList<ArrayList<Integer>> readGraphToAdjList(String resourcePath) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        Set<Integer> vertices = new HashSet<>();

        InputStream is = GraphReader.class.getResourceAsStream(resourcePath);
        if (is == null) {
            System.err.println("Error: Could not find resource file: " + resourcePath);
            return adj;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    int v1 = Integer.parseInt(parts[0]);
                    int v2 = Integer.parseInt(parts[1]);
                    vertices.add(v1);
                    vertices.add(v2);
                }
            }

            // Initialize adjacency list with empty lists
            for (int i = 0; i <= vertices.stream().max(Integer::compare).orElse(0); i++) {
                adj.add(new ArrayList<>());
            }

            // Reset file reader to start of file
            is = GraphReader.class.getResourceAsStream(resourcePath);
            if (is == null) {
                System.err.println("Error: Could not reopen resource file: " + resourcePath);
                return adj;
            }
            BufferedReader newBr = new BufferedReader(new InputStreamReader(is));

            // Read file again to populate adjacency list
            while ((line = newBr.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    int v1 = Integer.parseInt(parts[0]);
                    int v2 = Integer.parseInt(parts[1]);
                    adj.get(v1).add(v2);
                    adj.get(v2).add(v1); // Remove this line if the graph is directed
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number in the file: " + e.getMessage());
        }

        return adj;
    }

    public static void main(String[] args) {
        String resourcePath = "graph.txt";
        ArrayList<ArrayList<Integer>> adjacencyList = readGraphToAdjList(resourcePath);

        if (adjacencyList.isEmpty()) {
            System.out.println("The adjacency list is empty. Check if the file exists and is correctly formatted.");
        } else {
            // Print the adjacency list
            for (int i = 0; i < adjacencyList.size(); i++) {
                System.out.print("Vertex " + i + ": ");
                for (int j : adjacencyList.get(i)) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }
        }
    }
}