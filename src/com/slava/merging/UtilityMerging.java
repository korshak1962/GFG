package com.slava.merging;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class UtilityMerging {

    static public <T extends Comparable> ArrayList<T> merge(List<Mergeable<T>> listOfArrays) {
        PriorityQueue<Mergeable> minHeap = new PriorityQueue<>();
        for (Mergeable mergeable : listOfArrays) {
            minHeap.add(mergeable);
        }
        ArrayList<T> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Mergeable<T> mergeable = minHeap.poll();
            result.add(mergeable.poll());
            if (mergeable.peek() != null) {
                minHeap.add(mergeable);
            }
        }
        return result;
    }
}
