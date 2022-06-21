package com.slava.combination;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Permutations {

    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> updatedResult = new ArrayList<>();
            for (List<Integer> lst : result) {
                for (int i = 0; i < lst.size() + 1; i++) {
                    List<Integer> newList = new LinkedList(lst);
                    newList.add(i, num);
                    updatedResult.add(newList);
                }
            }
            result.clear();
            result.addAll(updatedResult);
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Permutations.findPermutations(new int[]{1, 3, 5});
        System.out.print("Here are all the permutations: " + result);
    }
}
