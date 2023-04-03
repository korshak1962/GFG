package com.slava;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InterviewNotes {

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

    class Interval implements Comparable {
        public int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
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

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Interval)) return 0;
            Interval interval = (Interval) o;
            return start - interval.start;
        }

        @Override
        public String toString() {
            return this.start + "," + this.end;
        }
    }

    @Test
    public void testArray() {
        //protected List<Edge>[] graph;
        Interval[] ar = new Interval[3];
        ar[0] = new Interval(4, 8);
        ar[1] = new Interval(10, 2);
        ar[2] = new Interval(2, 3);
        //  ar.length;
        Arrays.sort(ar, 1, 3); // is Interval comparable
        Arrays.sort(ar, Comparator.comparing(Interval::getStart).reversed().
                thenComparing(Interval::getEnd));
        Arrays.sort(ar, (a, b) -> {
            if (a.start == b.start) return 0;
            if (a.start > b.start) return 1;
            else return -1;
        });
        Interval inter = new Interval(1, 2);
        int i = Arrays.binarySearch(ar, 1, 2, inter);// (-(insertion point) - 1)
        //Integer.max()
        //  Arrays.copyOfRange()
        int index = Arrays.binarySearch(ar, inter, Collections.reverseOrder());
    }

    @Test
    public void testHeap() {
        PriorityQueue<Interval> intervals = new PriorityQueue<>();  //comparator
        Interval inter = new Interval(1, 2);
        intervals.add(inter);
        inter = intervals.peek();
        inter = intervals.poll();
        PriorityQueue<Interval> heap = new PriorityQueue<>(Comparator.comparing(Interval::getEnd));
        heap.size();
        heap.peek();
        heap.remove(inter);
    }

    @Test
    public void testCollections() {
        List<Interval> intervals = new LinkedList<>();
        Collections.sort(intervals, Comparator.comparing(Interval::getStart).reversed());
        Interval inter = new Interval(3, 4);
        Collections.binarySearch(intervals, inter, Comparator.comparing(Interval::getStart));
        ((Deque<Interval>) intervals).addFirst(inter);
        ((Deque<Interval>) intervals).pollFirst();

        ((Deque<Interval>) intervals).addFirst(inter);
        ((Deque<Interval>) intervals).addFirst(new Interval(4, 5));
        //  intervals.retainAll(new ArrayList<>());
        intervals.listIterator(1).next();
        Interval max = Collections.max(intervals);
        Collections.sort(intervals, Comparator.comparing(Interval::getStart));
        System.out.println(intervals.size());
        Iterator<Interval> iter = intervals.iterator();
        Interval prev = iter.next();
        while (iter.hasNext()) {
            Interval current = iter.next();
            if (prev.end >= current.start) {
                prev.end = current.end;
                iter.remove();
                continue;
            }
            prev = current;
        }
        System.out.println(intervals.size());
        System.out.println(intervals.get(0));
    }

    @Test
    public void testStream() {
        List<Interval> inters1 = new LinkedList<>();
        inters1.add(new Interval(1, 2));
        inters1.add(new Interval(0, 2));
        Set<Integer> starts = inters1.stream().sorted().map(a -> a.start).collect(Collectors.toSet());
        System.out.println(starts);

        List<List<Interval>> listList = new ArrayList<>();
        List<Interval> inters2 = new LinkedList<>();
        inters2.add(new Interval(1, 8));
        inters2.add(new Interval(0, 7));
        listList.add(inters1);
        listList.add(inters2);
        List<Integer> ends = listList.stream().sorted((i1, i2) -> i1.size() - i2.size())
                .flatMap(listInt -> listInt.stream()).sorted(((in1, in2) -> in2.end - in1.end)).map(in -> in.end).collect(Collectors.toList());
        System.out.println(ends);

        boolean res = IntStream.range(1, 2).allMatch(e -> e > 0);
        System.out.println(res);
    }


    public List<Integer> findWord(String textString, String word) {
        List<Integer> indexes = new ArrayList<Integer>();
        String lowerCaseTextString = textString.toLowerCase();
        String lowerCaseWord = word.toLowerCase();

        int index = 0;
        while (index != -1) {
            index = lowerCaseTextString.indexOf(lowerCaseWord, index);
            if (index != -1) {
                indexes.add(index);
                index++;
            }
        }
        return indexes;
    }

    @Test
    public void testFindWord() {
        System.out.println(findWord("abababc", "aba"));
        Integer.valueOf("123");
    }

    @Test
    public void testList() {
        List<Interval> intervals = new LinkedList<>();
        intervals.add(0, new Interval(1, 2));
        List<Interval> intervals2 = new LinkedList<>();
        intervals2.add(0, new Interval(-1, -2));
        intervals2.add(0, new Interval(-3, -4));
        intervals.addAll(0, intervals2);
        System.out.println(intervals);
    }

    //https://leetcode.com/problems/buddy-strings/submissions/863948164/
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;
        Set<Character> setChar = new HashSet<>();
        List<Character> difList1 = new ArrayList<>();
        List<Character> difList2 = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            setChar.add(s.charAt(i));
            if (s.charAt(i) != goal.charAt(i)) {
                difList1.add(s.charAt(i));
                difList2.add(goal.charAt(i));
            }
        }
        if (setChar.size() < s.length() && difList1.size() == 0) {
            return true;
        }
        if (difList1.size() != 2) return false;
        if (new HashSet<>(difList1).equals(new HashSet<>(difList2))) {
            return true;
        }
        return false;
    }

    @Test
    public void testSet() {
        Set<Integer> s1 = new HashSet<>();
        s1.add(1);
        s1.add(2);
        Set<Integer> s2 = new HashSet<>();
        s2.add(2);
        s2.add(3);
        s1.retainAll(s2);
        System.out.println(s1);
    }
}
