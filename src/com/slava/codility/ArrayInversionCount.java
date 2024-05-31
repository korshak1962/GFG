package com.slava.codility;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ArrayInversionCount {

    // https://app.codility.com/demo/results/trainingXUCEQD-AQT/   54% , correct but slow
    // https://app-eu.codility.com/demo/results/trainingSWB9VY-ZPC/  100%
    // could be solved w/o class IndexToValue, just sort the list of ints (simpler)

    public int solution(int[] A) {
        int result = 0;
        List<IndexToValue> indexToValues = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            indexToValues.add(new IndexToValue(i, A[i]));
        }
        Collections.sort(indexToValues);
        for (int curInd = 0; curInd < A.length; curInd++) {
            IndexToValue key = new IndexToValue(0, A[curInd]);
            int sortedIndex = getMinInd(indexToValues, key);
            result += sortedIndex;
            indexToValues.remove(sortedIndex);
            if (result > 1000000000) return -1;
        }
        return result;
    }


    static class IndexToValue implements Comparable<IndexToValue> {
        int index;
        int value;

        IndexToValue(int index, int value) {
            this.value = value;
            this.index = index;
        }

        public String toString() {
            return "index=" + index + " value=" + value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            //  if (o == null || getClass() != o.getClass()) return false;
            IndexToValue that = (IndexToValue) o;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }


        @Override
        public int compareTo(IndexToValue o) {
            return this.value - o.value;
        }
    }

    private static <T> int getMaxInd(List<? extends Comparable<? super T>> numsWithDupSorted, T key) {
        int indToInsert = Collections.binarySearch(numsWithDupSorted, key);
        while (indToInsert < numsWithDupSorted.size() - 1 && numsWithDupSorted.get(indToInsert + 1).equals(key)) {
            List<? extends Comparable<? super T>> subList = numsWithDupSorted.subList(indToInsert + 1, numsWithDupSorted.size());
            indToInsert += Collections.binarySearch(subList, key) + 1;
        }
        return indToInsert;
    }

    private static <T> int getMinInd(List<? extends Comparable<? super T>> numsWithDupSorted, T key) {
        int curInd = Collections.binarySearch(numsWithDupSorted, key);
        while (curInd > 0 && numsWithDupSorted.get(curInd - 1).equals(key)) {
            List<? extends Comparable<? super T>> subList = numsWithDupSorted.subList(0, curInd);
            curInd = Collections.binarySearch(subList, key);
        }
        return curInd;
    }

    @Test
    public void testDubSearchMax() {
        List<Integer> intList = List.of(1, 1, 2, 2, 3, 3, 4, 4, 4);
        int res = getMaxInd(intList, 4);
        System.out.println(res);
    }

    @Test
    public void testDubSearchMin() {
        List<Integer> intList = List.of(1, 1, 2, 2, 3, 3, 4, 4, 4);
        int res = getMinInd(intList, 4);
        System.out.println(res);
    }

    @Test
    public void testDubSearchMin1() {
        List<Integer> intList = List.of(1, 1, 1);
        int res = getMinInd(intList, 1);
        System.out.println(res);
    }

    @Test
    public void testDubSearchMinObj() {
        List<IndexToValue> intList = List.of(
                new IndexToValue(0, 1), new IndexToValue(1, 1),
                new IndexToValue(2, 2), new IndexToValue(3, 2),
                new IndexToValue(4, 3), new IndexToValue(5, 3)
        );
        int res = getMinInd(intList, new IndexToValue(0, 3));
        System.out.println(res);
    }

    @Test
    public void testSolution0() {
        int A[] = {-1, 6, 3, 4, 7, 4};
        int res = solution(A);
        Assert.assertEquals(4, res);
    }

    //For example, for the input [5, 4, 3, 2, 1]  expected 10).
    @Test
    public void testSolution() {
        int A[] = {5, 4, 3, 2, 1};
        int res = solution(A);
        Assert.assertEquals(10, res);
    }

    //For example, for the input [5, 4, 3, 2, 1]  expected 10).
    @Test
    public void testSolutionAsc() {
        int A[] = {1, 2, 3, 4, 6, 5, 6};
        int res = solution(A);
        Assert.assertEquals(1, res);
    }

    @Test
    public void testSolution1() {
        int A[] = {1, 1, 1};
        int res = solution(A);
        System.out.println(res);
    }

    public static int solutionOld(int[] A) {
        int res = 0;
        List<IndexToValue> indexTovalues = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            indexTovalues.add(new IndexToValue(i, A[i]));
        }
        Collections.sort(indexTovalues, (a, b) -> a.value - b.value);
        //System.out.println(indexTovalues);
        List<Integer> indsSoFar = new ArrayList<>(A.length);
        //System.out.println("size="+indsSoFar.size());
        int indToInsert = 0;
        for (IndexToValue iv : indexTovalues) {
            indToInsert = Collections.binarySearch(indsSoFar, iv.index);
            if (indToInsert < 0) indToInsert = Math.abs(indToInsert) - 1;
            indsSoFar.add(indToInsert, iv.index);
            res += Math.abs(indToInsert - iv.index);
        }
        return res;
    }

}
