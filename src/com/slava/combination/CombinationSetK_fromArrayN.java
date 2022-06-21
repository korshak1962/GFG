package com.slava.combination;

import org.junit.Test;

import java.util.*;

//https://algorithms.tutorialhorizon.com/print-all-combinations-of-subset-of-size-k-from-given-array/
public class CombinationSetK_fromArrayN {

    public void subset(int[] A, int k, int start, int currLen, boolean[] used) {
        if (currLen == k) {
            for (int i = 0; i < A.length; i++) {
                if (used[i] == true) {
                    System.out.print(A[i] + " ");
                }
            }
            System.out.println();
            return;
        }
        if (start == A.length) {
            return;
        }
        // For every index we have two options,
        // 1.. Either we select it, means put true in used[] and make currLen+1
        used[start] = true;
        subset(A, k, start + 1, currLen + 1, used);
        // 2.. OR we dont select it, means put false in used[] and dont increase
        // currLen
        used[start] = false;
        subset(A, k, start + 1, currLen, used);
    }

    public static void main(String[] args) {
        int A[] = {1, 2, 3, 4, 5, 6};
        boolean[] B = new boolean[A.length];
        CombinationSetK_fromArrayN combinationSetK_fromArrayN = new CombinationSetK_fromArrayN();
        combinationSetK_fromArrayN.subset(A, 3, 0, 0, B);

    }

    @Test
    public void testgetKcombinationFromOfN_Wrong() {
        //String A[] = {"1", "2", "3", "4", "5", "6"};
        String A[] = {"1", "2", "3", "4"};
        Set<String> from = new HashSet<String>(Arrays.asList(A));
        int k = 3;
        List<List<String>> result = getKcombinationFromOfNWrong(k, from);
        for (List<String> lst : result) {
            System.out.println(lst);
        }

    }

    @Test
    public void testgetKcombinationFromOfN() {
        String A[] = {"1", "2", "3", "4", "5", "6"};
        //String A[] = {"1", "2", "3"};
        List<String> from = Arrays.asList(A);
        int k = 2;
        List<List<String>> result = getKCombinationFromN(k, from);
        int num = 0;
        Set<Set<String>> resultSet = new HashSet<>();
        for (List<String> lst : result) {
            final boolean add = resultSet.add(new HashSet(result));
            if (!add) {
                System.out.println("not added" + lst);
                // throw new RuntimeException();
            }
            System.out.println(lst);
            num++;
        }
        System.out.println(num);

    }

    //correct with SubList
    public static List<List<String>> getKCombinationFromN(int sizeOfCombination, List<String> fromList) {
        List<List<String>> result = new ArrayList<>();
        if (sizeOfCombination == 1) {
            for (String str : fromList) {
                List<String> lst = new ArrayList<>();
                lst.add(str);
                result.add(lst);
            }
            return result;
        }
        for (int i = sizeOfCombination - 1; i < fromList.size(); i++) {
            final List<String> subList = fromList.subList(0, i);
            final List<List<String>> kCombinationFromN = getKCombinationFromN(sizeOfCombination - 1, subList);
            for (List<String> lst : kCombinationFromN) {
                lst.add(fromList.get(i));
                result.add(lst);
            }
        }
        return result;
    }

    public static List<List<String>> getKCombinationFromNRecursion(int sizeOfCombination, List<String> fromList) {
        List<List<String>> result = new ArrayList<>();
        if (sizeOfCombination == 1) {
            for (String str : fromList) {
                List<String> lst = new ArrayList<>();
                lst.add(str);
                result.add(lst);
            }
            return result;
        }

      //  char c = fromList.(0);
        return null;
    }

    //wrong
    public static List<List<String>> getKcombinationFromOfNWrong(int k, Set<String> from) {
        List<List<String>> result = new ArrayList<>();
        if (k == 1) {
            for (String str : from) {
                List<String> lst = new ArrayList<>();
                lst.add(str);
                result.add(lst);
            }
            return result;
        }
        for (String str : from) {
            Set<String> newFrom = new HashSet<>(from);
            newFrom.remove(str);
            for (List<String> lst : getKcombinationFromOfNWrong(k - 1, newFrom)) {
                lst.add(str);
                result.add(lst);
            }
        }
        return result;
    }

}
