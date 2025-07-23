package com.slava.two_pointers;

import org.junit.Test;

import java.util.*;

//https://www.geeksforgeeks.org/problems/count-pairs-with-given-sum5022/1
public class SumOfTwo {
    public static ArrayList<ArrayList<Integer>> getPairs(int[] arr) {
        ArrayList<ArrayList<Integer>> forReturn = new ArrayList<>();
        long count = Arrays.stream(arr).filter(x -> x == 0).count();
        if (count > 1) {
            forReturn.add(new ArrayList<>(Arrays.asList(0, 0)));
        }

        int[] nonDup = Arrays.stream(arr).distinct().sorted().toArray();
        int lowIndex = 0;
        int upperIndex = nonDup.length - 1;
        while (lowIndex < upperIndex) {
            int sum = nonDup[lowIndex] + nonDup[upperIndex];
            if (sum == 0) {
                ArrayList<Integer> toAdd = new ArrayList<>();
                toAdd.add(nonDup[lowIndex]);
                toAdd.add(nonDup[upperIndex]);
                forReturn.add(toAdd);
                lowIndex++;
                upperIndex--;
            } else if (sum > 0) {
                upperIndex--;

            } else {
                lowIndex++;
            }
        }
        forReturn.sort(Comparator.comparing(list -> list.get(0)));
        return forReturn;
    }

    @Test
    public void test() {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        System.out.println(getPairs(arr));
    }

}
