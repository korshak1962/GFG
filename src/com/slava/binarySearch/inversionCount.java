package com.slava.binarySearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class inversionCount {
    static long inversionCount(long A[], long N) {
        long res = 0;
        long Aclone[] = A.clone();
        Arrays.sort(Aclone);
        Map<Long, List<Integer>> ValueToIndexes = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            ValueToIndexes.computeIfAbsent(A[i], k -> new ArrayList<>());
            ValueToIndexes.get(A[i]).add(i);
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] != Aclone[i]) {
                List<Integer> indexesOriginal = ValueToIndexes.get(Aclone[i]);
                int indexToSwap = -1;
                for (int index : indexesOriginal) {
                    if (A[index] != Aclone[index]) {
                        indexToSwap = index;
                        break;
                    }
                }
                swap(A, indexToSwap, i,ValueToIndexes);
                res++;
            }
        }
        return res;

    }

    static void swap(long[] A, int from, int to,Map<Long, List<Integer>> ValueToIndexes) {
        long tmp = A[to];
        ValueToIndexes.get(A[to]).remove((Integer)to);
        ValueToIndexes.get(A[from]).remove((Integer)from);
        A[to] = A[from];
        ValueToIndexes.get(A[to]).add(to);
        A[from] = tmp;
        ValueToIndexes.get(A[from]).add(from);
    }

    @Test
    public void test0() {
        long A[] = {2, 4, 1, 3, 5};
        long res = inversionCount(A, A.length);
        Assert.assertEquals(3, res);
    }
    @Test
    public void testSameValue() {
        long A[] = {10,10,10};
        long res = inversionCount(A, A.length);
        Assert.assertEquals(0, res);
    }
}
