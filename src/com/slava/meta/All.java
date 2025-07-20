package com.slava.meta;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
1.string = "1+2*3" - calc str with only + and *
2. Diameter of binary tree
3. Range in BST
4. min of depature and return  d[]={12,9,8,23,25}  r[]={25,66,5,77,88,99}
5. get int[] from two  merging BST
6. design crawler
7. The most dificult task,action from manager, best accomplishment
 */

public class All {

    /// search in sorted array
    int findFirst(int ar[], int v) {
        int lower = 0;
        int upper = ar.length - 1;
        int current = (upper + lower) / 2;
        if (ar[0] == v) return 0;
        while (upper > lower) {
            if (ar[current] > v) {
                upper = current - 1;
            } else if (ar[current] == v) {
                if (ar[current - 1] != v) return current;
                upper = current - 1;
            } else if (ar[current] < v) {
                lower = current + 1;
            }
            current = (upper + lower) / 2;
        }
        if (ar[current] != v) return -(current + 1);
        return current;
    }

    @Test
    public void test() {
        int[] ar = {1, 1, 1, 2, 2, 2, 5, 6, 7};
        int res = findFirst(ar, 2);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test2() {
        int[] ar = {1, 1, 1, 2, 2, 2, 5, 6, 7};
        int res = findFirst(ar, 7);
        Assert.assertEquals(8, res);
    }

    @Test
    public void test3() {
        int[] ar = {1, 1, 1, 2, 2, 2, 5, 6, 7};
        int res = findFirst(ar, 25);
        Assert.assertEquals(-9, res);
    }

    //=================================


    class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

    // finf max meeting rooms
    int maxRoom(int[] start, int[] end) {
        int res = 1;
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            intervals.add(new Interval(start[i], end[i]));
        }
        intervals.sort(Comparator.comparing(Interval::getStart));
        PriorityQueue<Interval> minHeapEnd = new PriorityQueue<>(Comparator.comparing(Interval::getEnd));
        for (Interval inter : intervals) {
            if (minHeapEnd.isEmpty()) {
                minHeapEnd.add(inter);
                continue;
            }
            while (minHeapEnd.peek().end < inter.start) {
                minHeapEnd.poll();
            }
            minHeapEnd.add(inter);
            res = Integer.max(res, minHeapEnd.size());
        }
        return res;
    }

    @Test
    public void test1() {
        int[] start = {1, 1, 1, 2, 2, 2, 5, 6, 7};
        int[] end = {10, 1, 1, 2, 2, 2, 5, 6, 7};
        int res = maxRoom(start, end);
        System.out.println(res);
        // Assert.assertEquals(3, res);
    }

    //
    class Trie {
        Map<Character, Trie> charToChild = new HashMap<>();
        boolean endWord;

        public void add(List<Character> chars) {
            if (chars.isEmpty()) return;
            charToChild.computeIfPresent(chars.removeFirst(), (key, trie) -> {
                trie.add(chars);
                return trie;
            });
            charToChild.computeIfAbsent(chars.removeFirst(), (key) ->
            {
                Trie child = new Trie();
                child.add(chars);
                return child;
            });
        }

        public List<String> getByPrefix(String prefix) {
            return null;
        }

    }

    //======================================================


    static class ProbabilityByPopulation {

        Map<String, Double> cityToPopulation = new HashMap<>();
        double totalPopulation;
        NavigableMap<Double, String> probabiltyToCity = new TreeMap<>();

        public void addCity(String city, double population) {
            cityToPopulation.put(city, population);
            totalPopulation = cityToPopulation.values().stream().reduce(0d, Double::sum);
            // cityToPopulation.values().stream().mapToDouble(Double::doubleValue).sum();
            double intervalValue = 0d;
            probabiltyToCity.clear();
            for (Map.Entry<String, Double> entry : cityToPopulation.entrySet()) {
                probabiltyToCity.put(intervalValue, entry.getKey());
                intervalValue += entry.getValue() / totalPopulation;
            }
        }

        public String getRandomCity() {
            return probabiltyToCity.floorEntry(Math.random()).getValue();
        }
    }

    @Test
    public void testProbabilityByPopulation() {
        ProbabilityByPopulation probabilityByPopulation = new ProbabilityByPopulation();
        probabilityByPopulation.addCity("Kolpino", 100);
        probabilityByPopulation.addCity("Piter", 1000);

        for (int i = 0; i < 20; i++) {
            System.out.println(probabilityByPopulation.getRandomCity());
        }
    }

    static class NumberToList {
        int num;
        Iterator<Integer> iter;

        NumberToList(int num, Iterator<Integer> iter) {
            this.num = num;
            this.iter = iter;
        }

        public int getNum() {
            return num;
        }
    }

    List<Integer> mergeLists(List<Queue<Integer>> lists) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Queue<Integer>> heapLnk = new PriorityQueue<>(
                Comparator.nullsLast(Comparator.comparing(Queue::peek)));
        heapLnk.addAll(lists);
        Queue<Integer> top = heapLnk.poll();
        while (top != null && top.peek() != null) {
            result.add(top.poll());
            if (top.peek() != null) heapLnk.add(top);
            top = heapLnk.poll();
        }
        return result;
    }

    @Test
    public void testLnk() {
        Deque<Integer> lst1 = new LinkedList<>(List.of(1, 2));
        Deque<Integer> lst2 = new LinkedList<>(List.of(3, 4, 8));
        Deque<Integer> lst3 = new LinkedList<>(List.of(5, 5, 5, 6));
        List<Queue<Integer>> listOfLists = new LinkedList<>();
        listOfLists.add(lst1);
        listOfLists.add(lst2);
        listOfLists.add(lst3);
        System.out.println(mergeLists(listOfLists));
    }

}
