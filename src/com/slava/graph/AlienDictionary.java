package com.slava.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

// https://practice.geeksforgeeks.org/problems/alien-dictionary/1
public class AlienDictionary {

    @Test
    public void testFindOrder() {
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        String[] dict1 = {"asd", "bcd", "bef"};
        System.out.println(findOrder(dict1, 1, 1));
    }

    public String findOrder(String[] dict, int N, int K) {
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        // build graph and compute inDegree
        for (int i = 0; i < dict.length - 1; i++) {
            String prev = dict[i];
            String next = dict[i + 1];
            int minLength = Integer.min(prev.length(), next.length());
            for (int j = 0; j < minLength; j++) {
                if (next.charAt(j) != prev.charAt(j)) {
                    Character parent = prev.charAt(j);
                    if (!inDegree.keySet().contains(parent)) inDegree.put(parent, 0);
                    Character child = next.charAt(j);
                    if (graph.get(parent) == null) graph.put(parent, new ArrayList<>());
                    final List<Character> children = graph.get(parent);
                    if (!children.contains(child)) {
                        children.add(child);
                        inDegree.computeIfPresent(child, (k, v) -> ++v);
                        inDegree.computeIfAbsent(child, (k) -> 1);
                    }
                    break;
                }
            }
        }
        // traverse the graph
        StringBuffer result = new StringBuffer();
        LinkedList<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) queue.add(entry.getKey());
        }
        while (!queue.isEmpty()) {
            Character source = queue.poll();
            result.append(source);
            final List<Character> childs = graph.get(source);
            if (childs != null) for (Character child : childs) {
                inDegree.compute(child, (k, v) -> --v);
                if (inDegree.get(child) == 0) queue.add(child);
            }
        }
        return result.toString();
    }

//=============================

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nTest = sc.nextInt();
        for (int it = 0; it < nTest; it++) {
            int nWord = sc.nextInt();
            int k = sc.nextInt();
            String[] words = new String[nWord];
            for (int iW = 0; iW < nWord; iW++) {
                words[iW] = sc.next();
            }
            Graph graph = new Graph();
            new AlienDictionary().buildGraph(words, graph);
            if (hasCycle(graph))
                System.out.println(0);
            else System.out.println(1);
        }
        sc.close();
    }

    String findOrder(String[] dict) {

        return "";
    }

    class Vertex {
        Set<Vertex> childs = new HashSet<>();
        char key;
        boolean isVisited = false;

        Vertex(char key) {
            this.key = key;
        }

        void addChild(Vertex childVertex) {
            childs.add(childVertex);
        }

        @Override
        public String toString() {
            String childsStr = "";
            for (Vertex v : childs) {
                childsStr = childsStr + v.key + " ";
            }
            return "\nkey=" + key + "\nchilds=" + childsStr;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Vertex)) return false;
            Vertex vertex = (Vertex) o;
            return key == vertex.key;
        }

        @Override
        public int hashCode() {
            return key;
        }
    }

    static class Graph {
        Map<Character, Vertex> charkeyToVertice = new HashMap<>();

        @Override
        public String toString() {
            return charkeyToVertice.values().toString();
        }
    }

    void buildGraph(String[] dict, Graph graph) {
        for (int i = 0; i < dict.length - 1; i++) {
            int length = Integer.min(dict[i].length(), dict[i + 1].length());
            for (int indexChar = 0; indexChar < length; indexChar++) {
                if ((dict[i + 1].charAt(indexChar) != dict[i].charAt(indexChar))) {
                    if (indexChar == 0 || dict[i + 1].charAt(indexChar - 1) == dict[i].charAt(indexChar - 1)) {
                        Vertex parentVertex = graph.charkeyToVertice
                                .computeIfAbsent(dict[i].charAt(indexChar), (c) -> new Vertex(c));
                        Vertex childVertex = graph.charkeyToVertice
                                .computeIfAbsent(dict[i + 1].charAt(indexChar), (c) -> new Vertex(c));
                        parentVertex.addChild(childVertex);
                    }
                }
            }
        }
    }

    static boolean hasCycle(Graph graph) {
        for (Vertex vrtx : graph.charkeyToVertice.values()) {
            if (!vrtx.isVisited) {
                List<Vertex> visitedList = new LinkedList<>();
                if (hasCycle(vrtx, visitedList)) {
                    return true;
                }
            }
        }
        return false;
    }

    String findOrder(Graph graph) {
        String forReturn = "";
        for (Vertex vrtx : graph.charkeyToVertice.values()) {
            if (!vrtx.isVisited) {
                List<Vertex> visitedList = new LinkedList<>();
                if (hasCycle(vrtx, visitedList)) {
                    return forReturn;
                }
            }
        }
        return forReturn;
    }

    static boolean hasCycle(Vertex vrtx, List<Vertex> visitedList) {
        if (visitedList.contains(vrtx)) {
            return true;
        }
        visitedList.add(vrtx);
        for (Vertex child : vrtx.childs) {
            if (!child.isVisited && hasCycle(child, visitedList)) {
                return true;
            }
        }
        vrtx.isVisited = true;
        return false;
    }

    @Test
    public void testBuildGraph() {
        String[] dict = new String[4];
        dict[0] = "cde";
        dict[1] = "cf";
        dict[2] = "dde";
        dict[3] = "ddx";
        Graph graph = new Graph();
        new AlienDictionary().buildGraph(dict, graph);
        System.out.println(graph);
        final boolean hasCyckle = hasCycle(graph);
        System.out.println(hasCyckle);
        Assert.assertFalse(hasCyckle);
    }

    @Test
    public void testBuildGraphCykle() {
        String[] dict = new String[4];
        dict[0] = "cde";
        dict[1] = "cf";
        dict[2] = "dde";
        dict[3] = "cdx";
        Graph graph = new Graph();
        new AlienDictionary().buildGraph(dict, graph);
        System.out.println(graph);
        final boolean hasCyckle = hasCycle(graph);
        System.out.println(hasCyckle);
        Assert.assertFalse(hasCyckle);
    }

    //=============================================


    static Node rootNode = new Node('R');

    public static int isSorteable(String[] words, int k) {
        buildGraph(words, k);
        String[] wordsCopy = Arrays.copyOf(words, words.length);
        Collections.shuffle(Arrays.asList(wordsCopy));
        Arrays.sort(wordsCopy, myComparatorString.reversed());
        System.out.println(Arrays.toString(words));
        System.out.println(Arrays.toString(wordsCopy));
        if (Arrays.equals(words, wordsCopy)) {
            return 1;
        }
        return 0;
    }

    static void buildGraph(String[] words, int k) {
        char parentKey;
        char childKey;
        for (int i = 0; i < words.length - 1; i++) {
            parentKey = words[i].charAt(0);
            do {
                childKey = words[++i].charAt(0);
            } while (parentKey == childKey && i < words.length);
            addEdge(parentKey, childKey);
        }
        findEdges(words);
    }

    private static void findEdges(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            final String previuosWord = words[i];
            final String nextWord = words[i + 1];
            if (previuosWord.charAt(0) != nextWord.charAt(0)) {
                continue;
            }
            int length = Integer.min(previuosWord.length(), nextWord.length());
            int ii = 1;
            while (previuosWord.charAt(ii) == nextWord.charAt(ii) && ii < length) {
                ii++;
                continue;
            }
            addEdge(previuosWord.charAt(ii), nextWord.charAt(ii));
        }
    }

    private static void addEdge(char parentKey, char childKey) {
        Node parentNode = rootNode.getNodeForKey(parentKey);
        if (parentNode == null) {
            parentNode = new Node(parentKey);
            rootNode.childNodes.add(parentNode);
        }
        Node child = rootNode.getNodeForKey(childKey);
        if (child != null) {
            parentNode.childNodes.add(child);
            rootNode.childNodes.remove(child);
        } else {
            parentNode.childNodes.add(new Node(childKey));
        }
    }

    static class Node {
        char key;
        Set<Node> childNodes = new HashSet<>();

        Node(char key) {
            this.key = key;
        }

        Node getNodeForKey(char key) {
            if (key == this.key) {
                return this;
            }
            for (Node node : childNodes) {
                final Node nodeForKey = node.getNodeForKey(key);
                if (nodeForKey != null) {
                    return nodeForKey;
                }
            }
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return key == node.key;
        }

        @Override
        public int hashCode() {
            return key;
        }

    }

    public static Comparator<String> myComparatorString = new Comparator<String>() {
        public int compare(String str1, String str2) {
            for (int i = 0; i < str1.length() && i < str2.length(); i++) {
                final int compareChar = compareChars(str1.charAt(i), str2.charAt(i));
                if (compareChar == 0) {
                    continue;
                } else return compareChar;
            }
            if (str1.length() < str2.length()) {
                return 1;
            }
            if (str1.length() > str2.length()) {
                return -1;
            }
            return 0;
        }
    };

    static int compareChars(Character char1, Character char2) {
        if (char1 == char2) {
            return 0;
        }
        Node parent = rootNode.getNodeForKey(char1);
        if (parent == null) {
            return 0;
        }
        if (parent.getNodeForKey(char2) != null) {
            return 1;
        }
        parent = rootNode.getNodeForKey(char2);
        if (parent == null) {
            return 0;
        }
        if (parent.getNodeForKey(char1) != null) {
            return -1;
        }
        return 0;
    }

}
