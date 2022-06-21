package com.slava.codility;

import org.junit.Test;

import java.util.*;

public class MaxCounters {

 //   https://app.codility.com/demo/results/trainingFDEY6B-GTZ/

    public int[] solution(int N, int[] A) {
        int[] counters = new int[N];
        Map<Integer, Integer> indexToCounts = new HashMap<>();
        int max = 0;
        int localMax = 0;
        int i;
        Integer val;
        for (int index : A) {
            if (index == (N + 1)) {
                if (indexToCounts.values().isEmpty()) continue;
                max += localMax;  // increase the max and clear the map
                localMax = 0;
                indexToCounts.clear();
            } else {
                i = index - 1;
                val = indexToCounts.get(i);
                if (val != null) {  // if present
                    val++;
                    indexToCounts.put(i, val);
                    localMax = Integer.max(localMax, val);
                } else {   // if absent
                    indexToCounts.put(i, 1);
                    localMax = Integer.max(localMax, 1);
                }

            }
        }
        for (int index = 0; index < N; index++) {  // last run since last max
            val = indexToCounts.get(index);
            if (val == null) {
                counters[index] = max;
            } else {
                counters[index] = val + max;
            }
        }
        return counters;
    }

    public int[] solution2(int N, int[] A) {
        int[] counters = new int[N];
        Map<Integer, Integer> indexToCounts = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        int max = 0;
        for (int index : A) {
            if (index == (N + 1)) {
                //   if (indexToCounts.values().isEmpty()) continue;
                heap.addAll(indexToCounts.values());
                final Integer peek = heap.peek();
                heap.clear();
                max += peek == null ? 0 : peek;
                indexToCounts.clear();
            } else {
                indexToCounts.computeIfPresent(index - 1, (key, value) -> ++value);
                indexToCounts.computeIfAbsent(index - 1, (key) -> 1);
            }
        }
        for (int index = 0; index < N; index++) {
            Integer val = indexToCounts.get(index);
            if (val == null) {
                counters[index] = max;
            } else {
                counters[index] = val + max;
            }
        }
        return counters;
    }

    public int[] solution1(int N, int[] A) {
        int[] counters = new int[N];
        int max = 0;
        for (int index : A) {
            if (index == (N + 1)) {
                for (int i = 0; i < N; i++) {
                    counters[i] = max;
                }
            } else {
                counters[index - 1]++;
                max = Integer.max(max, counters[index - 1]);
            }
        }
        return counters;
    }

    @Test
    public void test6() {
        int[] res;
        int[] res1;
        int N = 5;
        int[] ar = {6, 6, 6, 6, 6, 6, 6};
        res = solution(N, ar);
        res1 = solution1(N, ar);
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(res1));
    }

    @Test
    public void test() {
        int N = 5;
        //  int[] ar = {3, 4, 4, 6, 1, 4, 4,6,6};
        int[] res;
        int[] res1;
   /*   res = solution(N, ar);
        res1 = solution1(N, ar);
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(res1)); */
        System.out.println("=====");
        int ar1[] = {3, 4, 4, 6, 1, 4, 3, 4, 4, 6, 1, 4, 4};
        res = solution(N, ar1);
        res1 = solution1(N, ar1);
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(res1));
    }
}
