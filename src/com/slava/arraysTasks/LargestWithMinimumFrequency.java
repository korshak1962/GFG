package com.slava.arraysTasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

//https://practice.geeksforgeeks.org/problems/frequency-game/1/?company[]=Google&company[]=Google&page=1&query=company[]Googlepage1company[]Google
public class LargestWithMinimumFrequency {


    @Test
    public void testYes() {
        int[] input = {2, 2,5, 50, 1};
        Assert.assertEquals(50, LargButMinFreq(input, input.length));
    }

    // Function to find largest number with minimum frequency
    public static int LargButMinFreq(int arr[], int n) {
        HashMap<Integer, Integer> valueToFreq = new HashMap<>();
        for (Integer val : arr) {
            valueToFreq.computeIfPresent(val, (k, v) -> ++v);
            valueToFreq.putIfAbsent(val, 1);
        }
        final LinkedList<Map.Entry<Integer, Integer>> entries = new LinkedList<>(valueToFreq.entrySet());
        Collections.sort(entries, (a, b) -> {
            int cmp1 = a.getValue().compareTo(b.getValue());
            if (cmp1 != 0) {
                return cmp1;
            } else {
                return (-1)*a.getKey().compareTo(b.getKey());
            }
        });
        return entries.get(0).getKey();

    }

    class ValueThenKeyComparator<K extends Comparable<? super K>,
            V extends Comparable<? super V>>
            implements Comparator<Map.Entry<K, V>> {

        public int compare(Map.Entry<K, V> a, Map.Entry<K, V> b) {
            int cmp1 = a.getValue().compareTo(b.getValue());
            if (cmp1 != 0) {
                return cmp1;
            } else {
                return a.getKey().compareTo(b.getKey());
            }
        }

    }

}
