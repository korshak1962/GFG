package com.slava.merging;

import org.junit.Test;

import java.util.*;

public class Merge2Arrays {

    @Test
    public void testMergeArrays() {
        int[] ar = {1, 5, 7, 7};
        int[] br = {0, 1, 2, 3};
        System.out.println(Arrays.toString(mergeArrays(ar, br)));
    }

    @Test
    public void testMergeArrays2() {
        List<Mergeable<Integer>> input = new ArrayList<>();
        Integer[] AR = {1, 5, 7, 7};
        Integer[] BR = {0, 1, 2, 3};
        input.add(new MergealeArray<>(AR));
        input.add(new MergealeArray<>(BR));
        System.out.println(UtilityMerging.merge(input));
    }

    public static int[] mergeArrays(int[] arrayA, int[] arrayB) {
        int[] result = new int[arrayA.length + arrayB.length];
        int indexA = 0;
        int indexB = 0;
        int indexResult = 0;
        while (indexA < arrayA.length && indexB < arrayB.length) {
            int intA = arrayA[indexA];
            int intB = arrayB[indexB];
            if (intA > intB) {
                result[indexResult] = intB;
                indexB++;
            } else {
                result[indexResult] = intA;
                indexA++;
            }
            indexResult++;
        }
        if (indexA < arrayA.length) {
            for (int i = indexA; i < arrayA.length; i++) {
                result[indexResult] = arrayA[i];
                indexResult++;
            }
        }
        if (indexB < arrayB.length) {
            for (int i = indexB; i < arrayB.length; i++) {
                result[indexResult] = arrayB[i];
                indexResult++;
            }
        }
        return result;
    }


    public static List<Integer> mergeArrays(List<Integer> a, List<Integer> b) {
        List<Integer> result = new LinkedList<>();
        int indexA = 0;
        int indexB = 0;
        while (indexA < a.size() && indexB < b.size()) {
            Integer integerA = a.get(indexA);
            Integer integerB = b.get(indexB);
            if (integerA > integerB) {
                result.add(integerB);
                indexB++;
            } else {
                result.add(integerA);
                indexA++;
            }
        }
        if (indexA < a.size()) {
            result.addAll(a.subList(indexA, a.size()));
        } else {
            result.addAll(b.subList(indexB, b.size()));
        }
        return result;
    }

    public static List<Integer> mergeArraysIterator(List<Integer> a, List<Integer> b) {
        List<Integer> result = new LinkedList<>();
        final Iterator<Integer> iteratorA = a.iterator();
        final Iterator<Integer> iteratorB = b.iterator();
        Integer fromA = iteratorA.next();
        Integer fromB = iteratorB.next();
        while (iteratorA.hasNext() || iteratorB.hasNext())
            if (fromA > fromB) {
                fromB = getInteger(result, iteratorA, iteratorB, fromB);
                continue;
            } else {
                fromA = getInteger(result, iteratorA, iteratorB, fromA);
                continue;
            }

        return result;
    }

    private static Integer getInteger(List<Integer> result, Iterator<Integer> iteratorA, Iterator<Integer> iteratorB, Integer fromB) {
        result.add(fromB);
        if (iteratorB.hasNext()) {
            return iteratorB.next();
        } else {
            while (iteratorA.hasNext()) {
                result.add(iteratorA.next());
                continue;
            }
        }
        return fromB;
    }

    @Test
    public void testGetNumberOfBalancingElements() {
        Integer[] ar = {1, 5, 7, 7};
        List<Integer> a = Arrays.asList(ar);
        Integer[] br = {0, 1, 2, 3};
        List<Integer> b = Arrays.asList(br);
        System.out.println(mergeArrays(a, b));
    }

}
