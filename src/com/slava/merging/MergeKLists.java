package com.slava.merging;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKLists {

    @Test
    public void test() {
        List<Integer> lst1 = List.of(1, 2);
        List<Integer> lst2 = List.of(3, 4);
        List<Integer> lst3 = List.of(5,5,5, 6);
        List<List<Integer>> listOfLists = new LinkedList<>();
        listOfLists.add(lst1);
        listOfLists.add(lst2);
        listOfLists.add(lst3);
        System.out.println(mergeLists(listOfLists));
    }

    static class Wrapper {
        List<Integer> list;
        int currentIndex = 0;

        public Wrapper(List<Integer> list) {
            this.list = list;
        }

        Integer pollValue() {
            if (currentIndex < list.size()) {
                Integer res = list.get(currentIndex);
                currentIndex++;
                return res;
            } else return null;
        }

        Integer peekValue() {
            if (currentIndex < list.size())
                return list.get(currentIndex);
            else return null;
        }

    }

    List<Integer> mergeLists(List<List<Integer>> listOfLists) {
        List<Integer> result = new LinkedList<>();
        PriorityQueue<Wrapper> minHeap = new PriorityQueue<>((w1, w2) -> w1.peekValue() - w2.peekValue());
        for (List<Integer> lst : listOfLists) {
            minHeap.add(new Wrapper(lst));
        }
        while (!minHeap.isEmpty()) {
            Wrapper polledWrapper = minHeap.poll();
            result.add(polledWrapper.pollValue());
            if (polledWrapper.peekValue() != null) {
                minHeap.add(polledWrapper);
            }
        }
        return result;
    }

}
