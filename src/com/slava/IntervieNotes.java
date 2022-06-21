package com.slava;

import org.junit.Test;

import java.util.*;

public class IntervieNotes {

    @Test
    public void testMap() {
        Map<Integer, Integer> intToInt = new TreeMap<>();
        intToInt.computeIfAbsent(1, (key) -> 3 + 1);
        intToInt.computeIfPresent(1, (k, v) -> v + 4);
        final NavigableMap<Integer, Integer> intToIntNavigable = (NavigableMap<Integer, Integer>) intToInt;
        int r = intToIntNavigable.firstEntry().getValue();
        r = intToIntNavigable.floorEntry(1).getValue();
        intToIntNavigable.descendingMap(); // reverse order
        intToIntNavigable.headMap(1, true); // before key
        intToIntNavigable.subMap(1, 5);
        intToIntNavigable.tailMap(2, true);
    }

    class Interval {
        public int start;
        int end;

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

    @Test
    public void testArray() {
        Interval[] ar = new Interval[10];
        Arrays.sort(ar, 2, 3);
        Arrays.sort(ar, Comparator.comparing(Interval::getStart).reversed().
                thenComparing(Interval::getEnd));
        Arrays.sort(ar, (a, b) -> {
            if (a.start == b.start) return 0;
            if (a.start > b.start) return 1;
            else return -1;
        });
        Interval inter = new Interval();
        Arrays.binarySearch(ar,2,4,inter);// (-(insertion point) - 1)
    }

    @Test
    public void testHeap() {
        PriorityQueue<Interval> intervals = new PriorityQueue<>();  //comparator
        Interval inter = new Interval();
        intervals.add(inter);
         inter = intervals.poll();
    }
    @Test
    public void testCollections() {
        List<Interval> inters=new LinkedList<>();
        Collections.sort(inters, Comparator.comparing(Interval::getStart).reversed());
        Interval inter = new Interval();
        Collections.binarySearch(inters,inter,Comparator.comparing(Interval::getStart));
    }

}
