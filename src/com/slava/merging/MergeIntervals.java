package com.slava.merging;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Demo class for merge intervals
 * created by Korshak Nov 5, 2020
 * Copyright Williams-Sonoma
 */
public class MergeIntervals {

    // it requires additional memory
    public static List<Interval> mergeIntervalsStream(List<Interval> intervals) {
        return intervals.stream()
            .sorted(Comparator.comparingInt(i -> i.start))
            .reduce(
                new ArrayList<Interval>(),
                (merged, interval) -> {
                    if (merged.isEmpty() || merged.get(merged.size() - 1).end < interval.start) {
                        merged.add(interval);
                    } else {
                        merged.get(merged.size() - 1).end = Math.max(merged.get(merged.size() - 1).end, interval.end);
                    }
                    return merged;
                },
                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                }
            );
    }

    @Test
    public void testSimple() {
        List<Interval> unmergedIntervals = new LinkedList<>();
        unmergedIntervals.add(new Interval(94133, 94133));
        unmergedIntervals.add(new Interval(94200, 94299));
        unmergedIntervals.add(new Interval(94600, 94600));
        List<Interval> actual = mergeIntervals(unmergedIntervals);
        Assert.assertEquals(unmergedIntervals, actual);
    }

    @Test
    public void testSimpleMerge() {
        List<Interval> unmergedIntervals = new LinkedList<>();
        unmergedIntervals.add(new Interval(226, 600));
        unmergedIntervals.add(new Interval(200, 299));
        unmergedIntervals.add(new Interval(133, 133));

        List<Interval> actual = mergeIntervals(unmergedIntervals);

        Assert.assertEquals(133, actual.getFirst().start);
        Assert.assertEquals(200, actual.getLast().start);
        Assert.assertEquals(600, actual.getLast().end);
    }

    @Test
    public void testMergeIntoOneInterval() {
        // I suppose [1,2] and [3,4] should be merged to [1,4] according to business logic
        List<Interval> unmergedIntervals = new LinkedList<>();
        unmergedIntervals.add(new Interval(1, 2));
        unmergedIntervals.add(new Interval(1, 4));
        List<Interval> actual = mergeIntervals(unmergedIntervals);

        Assert.assertEquals(1, actual.getFirst().start);
        Assert.assertEquals(4, actual.getFirst().end);
    }

    @Test
    public void testIntervalsWithTheSameStart() {
        List<Interval> unmergedIntervals = new LinkedList<>();
        unmergedIntervals.add(new Interval(1, 4));
        unmergedIntervals.add(new Interval(2, 5));
        unmergedIntervals.add(new Interval(1, 2));
        unmergedIntervals.add(new Interval(1, 3));
        unmergedIntervals.add(new Interval(1, 5));
        List<Interval> actual = mergeIntervals(unmergedIntervals);


        Assert.assertEquals(1, actual.getFirst().start);
        Assert.assertEquals(5, actual.getFirst().end);
        Assert.assertEquals(1,actual.size());
    }

    @Test
    public void testMergeEmptyInterval() {
        List<Interval> unmergedIntervals = new LinkedList<>();
        List<Interval> actual = mergeIntervals(unmergedIntervals);
        Assert.assertEquals(unmergedIntervals, actual);
    }

    @Test
    public void testNullInterval() {
        List<Interval> emptyIntervals = new LinkedList<>();
        List<Interval> actual = mergeIntervals(null);
        Assert.assertEquals(emptyIntervals, actual);
    }

    /**
     * value object keeps start and end of interval
     */
    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
/*
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

 */
    }


    public List<Interval> mergeIntervals(List<Interval> unmergedIntervals) {
        if (unmergedIntervals==null || unmergedIntervals.isEmpty()) return List.of();
        Collections.sort(unmergedIntervals, (i1, i2) -> i1.start - i2.start);
        Iterator<Interval> iter = unmergedIntervals.iterator();
        Interval current = iter.next();
        Interval next = null;
        while (iter.hasNext()) {
            next = iter.next();
            if (current.end - next.start >= 0) { //merge them
                current.end = Integer.max(current.end, next.end);
                iter.remove();
            } else {
                current = next;
            }
        }
        return unmergedIntervals;
    }

    /**
     * Merges Collection of Interval in case they overlapped or adjust like [1,2] and [3,4]
     *
     * @param unmergedIntervals
     * @return sorted list by start of interval, increased
     */
    public List<Interval> mergeIntervalsOld(Collection<Interval> unmergedIntervals) {
        List<Interval> merged = new LinkedList<>();
        if (unmergedIntervals == null || unmergedIntervals.isEmpty()) return merged;
        NavigableMap<Integer, Integer> startToEnd = new TreeMap<>();
        for (Interval interval : unmergedIntervals) {
            startToEnd.computeIfPresent(interval.start, (k, v) -> Integer.max(v, interval.end));
            startToEnd.putIfAbsent(interval.start, interval.end);
        }
        Map.Entry<Integer, Integer> firstEntry = startToEnd.firstEntry();
        int currentStart = firstEntry.getKey();
        int currentEnd = firstEntry.getValue();
        for (Map.Entry<Integer, Integer> entry : startToEnd.entrySet()) {
            if (currentEnd - entry.getKey() >= -1) {  // I suppose [1,2] and [3,4] should be merged to [1,4] according to business logic
                currentEnd = Integer.max(currentEnd, entry.getValue());

            } else {
                merged.add(new Interval(currentStart, currentEnd));
                currentStart = entry.getKey();
                currentEnd = entry.getValue();
            }
        }
        if (merged.isEmpty() || currentEnd != merged.get(merged.size() - 1).end) { //only one interval
            merged.add(new Interval(currentStart, currentEnd));
        }
        return merged;
    }
}
