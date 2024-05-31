package com.slava.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InversionCountSlowTrue {
    static long inversionCount(long A[], long N) {
        long res = 0;
        long[] aSorted = A.clone();
        Arrays.sort(aSorted);
        for (int i = 0; i < A.length; i++) {
            int minIndex = getMinIndArray(aSorted, A[i]);
            res += minIndex;
            System.arraycopy(aSorted, minIndex + 1, aSorted, minIndex, aSorted.length - minIndex - 1);
        }
        return res;
    }

    public static int getMinIndArray(long[] numsWithDupSorted, long key) {
        int curInd = Arrays.binarySearch(numsWithDupSorted, key);
        while (curInd > 0 && numsWithDupSorted[curInd - 1] == key) {
            curInd = Arrays.binarySearch(numsWithDupSorted, 0, curInd, key);
        }
        return curInd;
    }

    static long inversionCount1(long A[], long N) {
        long res = 0;
        List<Long> aSorted = new ArrayList<>(A.length);
        for (long a : A) {
            aSorted.add(a);
        }
        Collections.sort(aSorted);
        for (int i = 0; i < A.length; i++) {
            int minIndex = getMinInd(aSorted, A[i]);
            res += minIndex;
            aSorted.remove(minIndex);
        }
        return res;
    }

    public static <T> int getMinInd(List<? extends Comparable<? super T>> numsWithDupSorted, T key) {
        int curInd = Collections.binarySearch(numsWithDupSorted, key);
        while (curInd > 0 && numsWithDupSorted.get(curInd - 1).equals(key)) {
            List<? extends Comparable<? super T>> subList = numsWithDupSorted.subList(0, curInd);
            curInd = Collections.binarySearch(subList, key);
        }
        return curInd;
    }

    @Test
    public void test00() {
        long A[] = {3, 2, 1};
        long res = inversionCount(A, A.length);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test0() {
        long A[] = {2, 4, 1, 3, 5};
        long res = inversionCount(A, A.length);
        Assert.assertEquals(3, res);
    }

    @Test
    public void testSolution1() {
        long A[] = {-1, 6, 3, 4, 7, 4};
        long res = inversionCount(A, A.length);
        Assert.assertEquals(4, res);
    }

    @Test
    public void testSameValue() {
        long A[] = {10, 10, 10};
        long res = inversionCount(A, A.length);
        Assert.assertEquals(0, res);
    }

    @Test
    public void test1() {
        long A[] = {468, 335, 1, 170, 225, 479, 359, 463, 465, 206, 146, 282, 328, 462, 492, 496, 443, 328, 437, 392, 105, 403, 154, 293, 383, 422, 217, 219, 396, 448, 227, 272, 39, 370, 413, 168, 300, 36, 395, 204, 312, 323};
        long res = inversionCount(A, A.length);
        Assert.assertEquals(494, res);
    }
}
