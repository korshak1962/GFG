package com.slava;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private LinkedHashMap<Integer, Integer> cache;
    private int capacity;

    public LRUCache(int capacity) {

        //create cache with initial capacity of 16 items, load factor of 75% and using access order (LRU style) retrieval
        this.cache = new LinkedHashMap<Integer, Integer>(16,0.75f,true){

            //anonymous inner class to override removeEldestEntry behaivor.
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return  cache.size() > capacity;
            }
        };
        this.capacity = capacity;
    }

    public int get(int key) {
        int value = cache.getOrDefault(key, -1);
        return value;
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}
