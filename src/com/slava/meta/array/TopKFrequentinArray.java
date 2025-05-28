package com.slava.meta.array;

import org.junit.Test;

import java.util.*;

//https://www.geeksforgeeks.org/problems/top-k-frequent-elements-in-array/1
public class TopKFrequentinArray {

    public ArrayList<Integer> topKFrequent(int[] arr, int k) {
        Map<Integer, Integer> numToFreq = new HashMap<>();
        for (int a : arr) {
            numToFreq.computeIfPresent(a, (key, v) -> ++v);
            numToFreq.computeIfAbsent(a, key -> 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> entries = new PriorityQueue<>(
                Map.Entry.<Integer, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry::getKey,Comparator.reverseOrder()));
        entries.addAll(numToFreq.entrySet());
        ArrayList<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(entries.poll().getKey());
        }
        return result;
    }

    @Test
    public void test1(){
        int k = 2;
        int[] arr = {1, 1, 2, 2, 3, 3, 3, 4};
        ArrayList<Integer> res=topKFrequent(arr,k);
        System.out.println(res);
    }

    @Test
    public void test(){
        int k = 2;
        int[] arr = {6, 1, 1, 1, 2, 2, 3};
        ArrayList<Integer> res=topKFrequent(arr,k);
        System.out.println(res);
    }
}
