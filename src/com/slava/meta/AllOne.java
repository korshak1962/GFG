package com.slava.meta;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class AllOne {
    Map<String, StringToCount> keyToCount = new HashMap<>();
    final PriorityQueue<StringToCount> maxheap = new PriorityQueue<>(Comparator.comparing(StringToCount::getCount).reversed());
    final PriorityQueue<StringToCount> minheap = new PriorityQueue<>(Comparator.comparing(StringToCount::getCount));

    public AllOne() {
    }

    public void inc(String key) {
        StringToCount stringToCount = keyToCount.get(key);
        if (stringToCount == null) {
            stringToCount = new StringToCount(key, 0);
            maxheap.add(stringToCount);
            minheap.add(stringToCount);
            keyToCount.put(key, stringToCount);
        }
        stringToCount.count++;
    }

    public void dec(String key) {
        keyToCount.get(key).count--;
    }

    public String getMaxKey() {
        if (maxheap.isEmpty()) return "";
        maxheap.add(maxheap.poll());
        return maxheap.peek().key;
    }

    public String getMinKey() {
        while (!minheap.isEmpty() && minheap.peek().count < 1) {
            minheap.poll();
        }
        if (minheap.isEmpty()) return "";
        minheap.add(minheap.poll());
        return minheap.peek().key;
    }

    class StringToCount {
        String key;
        int count;

        public StringToCount(String key, int count) {
            this.key = key;
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */