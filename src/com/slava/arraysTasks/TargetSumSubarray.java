package com.slava.arraysTasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// google intervies 08.31.21
public class TargetSumSubarray {

    @Test
    public void test1() {
        int[] arr = {1, -1, 5, 10, -3, 2, 4};
        int targetSum = 7;
        Interval res = new Interval(3, 4);
        Assert.assertEquals(res, getIntervals(arr, targetSum).get(0));
    }

    @Test
    public void test2() {
        int[] arr = {1, -1, 5, 10, -3, 2, 4};
        int targetSum = 7;
        Interval res = new Interval(3, 4);
        Assert.assertEquals(res, getIntervals(arr, targetSum).get(0));
    }

    List<Interval> getIntervals(int[] arr, int targetSum) {
        List<Interval> result = new LinkedList<>();
        Map<Integer, List<Integer>> sumToIndexes = new HashMap<>();
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        List<Integer> v0 = new LinkedList<>();
        v0.add(0);
        sumToIndexes.put(prefixSum[0], v0);
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
            final int index = i;
            sumToIndexes.computeIfPresent(prefixSum[i], (k, v) -> {
                v.add(index);
                return v;
            });
            sumToIndexes.computeIfAbsent(prefixSum[i], (k) -> {
                List<Integer> v = new LinkedList<>();
                v.add(index);
                return v;
            });
        }
        for (int i = 0; i < arr.length; i++) {
            int toFindSum = prefixSum[i] + targetSum;
            final List<Integer> indexes = sumToIndexes.get(toFindSum);
            if (indexes != null)
                for (int endIndex : indexes) {
                    if (endIndex > i) result.add(new Interval(i+1, endIndex));
                }
        }
        return result;
    }


    class Interval {
        int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Interval)) return false;
            Interval interval = (Interval) o;
            if (start != interval.start) return false;
            return end == interval.end;
        }

        @Override
        public int hashCode() {
            int result = start;
            result = 31 * result + end;
            return result;
        }
    }
}
