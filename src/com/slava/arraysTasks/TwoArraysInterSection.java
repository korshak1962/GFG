package com.slava.arraysTasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoArraysInterSection {
    /*
    Common elements in 2 sorted integer arrays (time/space complexity):
int[] a = {1,2,5,8,20,30,100}; int[] b = {2, 6, 8, 20, 60};
int[] c = {2, 8, 20};

     */
    public int[] getInterSection(int[] A, int[] B) {
        List<Integer> toReturn = new ArrayList<>();
        Map<Integer, Integer> keyToNum = new HashMap<>();
        for (int a : A) {
            // keyToNum.computeIfPresent((k,v)->{v);
            final Integer numOccur = keyToNum.get(a);
            if (numOccur != null) {
                keyToNum.put(a, numOccur + 1);
            } else {
                keyToNum.put(a, 1);
            }
        }
        for (int b : B) {
            Integer numOccur = keyToNum.get(b);
            if (numOccur != null && numOccur > 0) {
                keyToNum.put(b, numOccur--);
                toReturn.add(b);
            }
        }
        return toReturn.stream().mapToInt((i) -> i).toArray();
    }

}
