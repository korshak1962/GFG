package com.slava.binarySearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
//https://app.codility.com/programmers/lessons/14-binary_search_algorithm/nailing_planks/
public class NailingPlanks {
    //   https://app.codility.com/demo/results/trainingJAD8C4-TEM/     62%
    // https://app.codility.com/demo/results/trainingT9KV6T-EBP/       100%
    @Test
    public void testSolution() {
        int A[] = new int[5];
        int B[] = new int[5];
        A[0] = 1;
        B[0] = 4;
        A[1] = 4;
        B[1] = 5;
        A[2] = 5;
        B[2] = 9;
        A[3] = 8;
        B[3] = 10;
        A[4] = 6;
        B[4] = 6;

        int C[] = new int[5];
        C[0] = 4;
        C[1] = 6;
        C[2] = 7;
        C[3] = 10;
        C[4] = 2;
        int correct = 4;
        int res = solution(A, B, C);
        Assert.assertEquals(correct, res);
    }

    @Test
    public void testSolutionPerf() {
        int nDim = 5000;
        int maxValue = 2 * nDim;
        int A[] = new int[nDim];
        int B[] = new int[nDim];
        int C[] = new int[nDim];
        int b1, b2 = 0;
        for (int i = 0; i < nDim; i++) {
            b1 = (int) (Math.random() * maxValue);
            b2 = (int) (Math.random() * maxValue);
            if (b1 > b2) {
                A[i] = b2;
                B[i] = b1;
            } else {
                A[i] = b1;
                B[i] = b2;
            }
            C[i] = (int) Math.random() * maxValue;
        }
        int res = solution(A, B, C);
        System.out.println(res);
    }

    @Test
    public void test1() {
        int A[] = new int[1];
        int B[] = new int[1];
        int C[] = new int[1];
        A[0] = 1;
        B[0] = 1;
        C[0] = 1;
        int correct = 1;
        int res = solution(A, B, C);
        Assert.assertEquals(correct, res);
    }
// 100%
    public int solution(int[] A, int[] B, int[] C) {
        long start = System.currentTimeMillis();
        int lowBondary = 0;
        int highBoundary = C.length;
        if (!ifAllPlanksNailed(A, B, C, highBoundary)) {
            return -1;
        }
        System.out.println("after first="+(System.currentTimeMillis()-start));
        while (lowBondary != highBoundary) {
            final int result = (highBoundary + lowBondary) / 2;
            if (ifAllPlanksNailed(A, B, C, result)) {
                highBoundary = result;
            } else {
                lowBondary = result;
                if (highBoundary - lowBondary == 1) {
                    break;
                }
            }
        }
        System.out.println("after ="+(System.currentTimeMillis()-start));
        return highBoundary;
    }

    private boolean ifAllPlanksNailed(int[] A, int[] B, int[] C, int result) {
        int[] cSum = fillCsum(C, result);
        for (int i = 0; i < A.length; i++) {
            if (A[i] == B[i]) {
                if ((A[i] > 0) && cSum[A[i]] - cSum[A[i] - 1] == 0) {
                    return false;
                }
                if (A[i]==0 && cSum[0] == 0) {
                    return false;
                }
                continue;
            }
            if (cSum[B[i]] == cSum[A[i] - 1]) {
                return false;
            }
        }
        return true;
    }

    private int[] fillCsum(int[] C, int result) {
        int[] cSum = new int[60001];
        for (int i = 0; i < result; i++) {
            cSum[C[i]] = 1;
        }
        for (int i = 1; i < cSum.length; i++) {
            cSum[i] += cSum[i - 1];
        }
        return cSum;
    }


    Map<Integer, List<Integer>> boundaryToPlunkIndex = new HashMap<>();
    int maxLength = 60001;
    int[] nailToNum = new int[maxLength];

    public int solution0(int[] A, int[] B, int[] C) {
        long start = System.currentTimeMillis();
        getNailToNum(C, maxLength);
        //       System.out.println("after getNailToNum="+(System.currentTimeMillis()-start));
        int intervalMinNailIndex;
        Map<Integer, Integer> plunkIndextToNailIndexes = new HashMap<>();
        int result = fillBoundaryToPlunkIndex(A, B);
        if (boundaryToPlunkIndex.isEmpty()) {
            return result;
        }
//        System.out.println("after fillBoundaryToPlunkIndex="+(System.currentTimeMillis()-start));
        final ArrayList<Map.Entry<Integer, List<Integer>>> entryArrayList = new ArrayList<>(boundaryToPlunkIndex.entrySet());
        Collections.sort(entryArrayList, Comparator.comparingInt(Map.Entry::getKey));
//        System.out.println("after sort="+(System.currentTimeMillis()-start));
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = entryArrayList.iterator();
        Map.Entry<Integer, List<Integer>> startEntryBoundaryToPlunkIndex = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> endEntryBoundaryToPlunkIndex = iterator.next();
            intervalMinNailIndex = getMinNailInexForInterval(nailToNum, startEntryBoundaryToPlunkIndex.getKey(), endEntryBoundaryToPlunkIndex.getKey());
            //update all existed
            final int intervalMinNailIndexFinal = intervalMinNailIndex;
            plunkIndextToNailIndexes.replaceAll(
                    (k, v) -> {
                        return Integer.min(v, intervalMinNailIndexFinal);
                    });
            //add all starts
            for (int plunkIndex : startEntryBoundaryToPlunkIndex.getValue()) {
                plunkIndextToNailIndexes.put(plunkIndex, intervalMinNailIndex);
            }
            // find max and remove
            for (int plunkIndex : endEntryBoundaryToPlunkIndex.getValue()) {
                final Integer nailIndex = plunkIndextToNailIndexes.get(plunkIndex);
                if (nailIndex != null) {
                    result = Integer.max(nailIndex, result);
                    plunkIndextToNailIndexes.remove(plunkIndex);
                }
            }
            startEntryBoundaryToPlunkIndex = endEntryBoundaryToPlunkIndex;
        }
//        System.out.println("after iterator="+(System.currentTimeMillis()-start));
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }

    private int fillBoundaryToPlunkIndex(int[] A, int[] B) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == B[i]) {
                result = Integer.max(nailToNum[i + 1], result);
                continue;
            }
            final int iFinal = i;
            boundaryToPlunkIndex.computeIfPresent(A[i], (k, v) -> {
                v.add(iFinal);
                return v;
            });
            boundaryToPlunkIndex.computeIfPresent(B[i], (k, v) -> {
                v.add(iFinal);
                return v;
            });
            boundaryToPlunkIndex.computeIfAbsent(A[i], (k) -> {
                List<Integer> v = new LinkedList<>();
                v.add(iFinal);
                return v;
            });
            boundaryToPlunkIndex.computeIfAbsent(B[i], (k) -> {
                List<Integer> v = new LinkedList<>();
                v.add(iFinal);
                return v;
            });
        }
        if (result == 0) {
            return -1;
        }
        return result;
    }

    private int getMinNailInexForInterval(int[] nailToNum, int start, int end) {
        int plunkMin = Integer.MAX_VALUE;
        for (int k = start; k <= end; k++) {
            if (nailToNum[k] != 0) {
                plunkMin = Integer.min(plunkMin, nailToNum[k]);
            }
        }
        return plunkMin;
    }

    private void getNailToNum(int[] C, int maxLength) {
        for (int i = 0; i < C.length; i++) {
            if (nailToNum[C[i]] == 0) {
                nailToNum[C[i]] = i + 1;
            }
        }
    }


    //==============================

    public int solution2(int[] A, int[] B, int[] C) {
        int result = 0;
        NavigableMap<Integer, Integer> nailToNum = new TreeMap<>();
        for (int i = 0; i < C.length; i++) {
            if (nailToNum.get(C[i]) == null) {
                nailToNum.put(C[i], i);
            }
        }
        Collection<Integer> numbers;
        for (int i = 0; i < A.length; i++) {
            numbers = nailToNum.subMap(A[i], true, B[i], true).values();
            if (numbers.isEmpty()) {
                return -1;
            }
            result = Integer.max(result, Collections.min(numbers));
        }
        return result + 1;
    }

    final int timeLimit = 1000;

    public int solution1(int[] A, int[] B, int[] C) {
        int result = 0;
        long start = System.currentTimeMillis();
        Map<Integer, List<Plunk>> intToPlunk = createIntToPlunk(A, B);
        final long afterCreateIntToPlunk = System.currentTimeMillis() - start;
        if (afterCreateIntToPlunk > timeLimit) {
            throw new RuntimeException("after createIntToPlunk=" + afterCreateIntToPlunk);
        }
        int nailed = 0;
        for (int nail : C) {
            result++;
            nailed += getNailed(intToPlunk, nail);
            if (nailed >= A.length) {
                return result;
            }
            final long nailCycle = System.currentTimeMillis() - start;
            if (nailCycle > timeLimit) {
                throw new RuntimeException("after createIntToPlunk=" + nailCycle + " result=" + result + " C.length=" + C.length);
            }
        }
        return -1;
    }

    private int getNailed(Map<Integer, List<Plunk>> intToPlunk, int nail) {
        int nailed = 0;
        final List<Plunk> plunks = intToPlunk.get(nail);
        if (plunks == null) {
            return 0;
        }
        for (Plunk plunk : plunks) {
            if (!plunk.isNailed) {
                nailed++;
                plunk.isNailed = true;
            }
        }
        return nailed;
    }

    private Map<Integer, List<Plunk>> createIntToPlunk(int[] A, int[] B) {
        Map<Integer, List<Plunk>> intToPlunk = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            Plunk plunk = new Plunk();
            for (int k = A[i]; k <= B[i]; k++) {
                intToPlunk.computeIfPresent(k, (key, plunks) -> {
                    plunks.add(plunk);
                    return plunks;
                });
                intToPlunk.computeIfAbsent(k, (key) ->
                {
                    List<Plunk> plunks = new LinkedList<>();
                    plunks.add(plunk);
                    return plunks;
                });
            }
        }
        return intToPlunk;
    }

    class Plunk {
        boolean isNailed = false;

        public Plunk() {
        }
    }

    double[][] array = {
            {1, 5},
            {13, 1.55},
            {12, 100.6},
            {12.1, .85}};

    void sortDouble() {

        java.util.Arrays.sort(array, new java.util.Comparator<double[]>() {
            public int compare(double[] a, double[] b) {
                return Double.compare(a[0], b[0]);
            }
        });
    }

}
