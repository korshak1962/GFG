package com.slava.merging;

import org.junit.Test;

import java.util.*;

public class MergeKArrays {

    @Test
    public void test() {
        Integer[] array1 = {1, 2};
        Integer[] array2 = {3, 4};
        Integer[] array3 = {5, 5, 5, 6};
        List<Integer[]> listOfArrays = new LinkedList<>();
        listOfArrays.add(array1);
        listOfArrays.add(array2);
        listOfArrays.add(array3);
        System.out.println(Arrays.toString(mergeArraysDoublePoll(listOfArrays)));
    }

    static class Wrapper {
        Integer[] array;
        int currentIndex = 0;

        public Wrapper(Integer[] array) {
            this.array = array;
        }

        Integer pollValue() {
            if (currentIndex < array.length) {
                Integer res = array[currentIndex];
                currentIndex++;
                return res;
            } else return null;
        }

        Integer peekValue() {
            if (currentIndex < array.length)
                return array[currentIndex];
            else return null;
        }
    }
/*
    Integer[] mergeArraysDoublePollNew(List<Integer[]> listOfArrays) {
        PriorityQueue<Integer[]> minHeap1 = new PriorityQueue<>((a1,a2)->a1[0]-a2[0]);
        int resLength = 0;
        for ( array : listOfArrays) {
            minHeap1.add(array);
            resLength += array.length;
        }
        Integer[] result = new Integer[resLength];
        int index = 0;
        while (!minHeap1.isEmpty()) {
            final Integer[] array  = minHeap1.poll();
            result[index] = deque.poll();
            index++;
            if (deque.peek() != null) {
                minHeap.add(deque);
            }
        }
        return result;
    }
*/

    Integer[] mergeArraysDoublePoll(List<Integer[]> listOfArrays) {
        PriorityQueue<Deque<Integer>> minHeap = new PriorityQueue<>((w1, w2) -> w1.peek() - w2.peek());
        int resLength = 0;
        for (Integer[] array : listOfArrays) {
            minHeap.add(new LinkedList<>(Arrays.asList(array)));
            resLength += array.length;
        }
        Integer[] result = new Integer[resLength];
        int index = 0;
        while (!minHeap.isEmpty()) {
            final Deque<Integer> deque = minHeap.poll();
            result[index] = deque.poll();
            index++;
            if (deque.peek() != null) {
                minHeap.add(deque);
            }
        }
        return result;
    }

    Integer[] mergeArraysOldStyle(List<Integer[]> listOfArrays) {
        PriorityQueue<Wrapper> minHeap = new PriorityQueue<>((w1, w2) -> w1.peekValue() - w2.peekValue());
        int resLength = 0;
        for (Integer[] array : listOfArrays) {
            minHeap.add(new Wrapper(array));
            resLength += array.length;
        }
        Integer[] result = new Integer[resLength];
        int index = 0;
        while (!minHeap.isEmpty()) {
            Wrapper polledWrapper = minHeap.poll();
            result[index] = polledWrapper.pollValue();
            index++;
            if (polledWrapper.peekValue() != null) {
                minHeap.add(polledWrapper);
            }
        }
        return result;
    }


}
