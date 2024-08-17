package com.slava.arraysTasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

//https://www.geeksforgeeks.org/problems/swapping-pairs-make-sum-equal4142/1?page=1&difficulty=Medium,Hard&sortBy=submissions
public class FindSwapValues {

    long findSwapValues(long a[], int n, long b[], int m) {
        if (a.length<2 || b.length<1) return -1;
        Set<Long> set1 = new HashSet<>(a.length);
        long sum1 = 0;
        for (long l : a) {
            sum1 += l;
            set1.add(l);
        }
        Set<Long> set2 = new HashSet<>(b.length);
        long sum2 = 0;
        for (long l : b) {
            sum2 += l;
            set2.add(l);
        }
        long diff = sum2 - sum1;
        if (diff % 2 != 0) {
            return -1L;
        }
        if (set1.stream()
                .anyMatch((s1) -> set2.contains(s1 + diff / 2))) {
            return 1;
        }
        return -1L;
    }

    @Test
    public void testPositive() {
        long a[] = {4, 1, 2, 1, 1, 2};
        long b[] = {3, 6, 3, 3};
        Assert.assertEquals(1, findSwapValues(a, a.length, b, b.length));
    }

    @Test
    public void testNegative() {
        long a[] = {14};
        long b[] = {4};
        Assert.assertEquals(-1, findSwapValues(a, a.length, b, b.length));
    }
}
