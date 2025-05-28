package com.slava.meta;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

//https://www.metacareers.com/profile/coding_puzzles?puzzle=958513514962507&source=career_site_login_panel_sign_up_button
public class LRUCache_Kaiten {
    class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int maxSize;

        public LRUCache(int maxSize) {
            // true for access-order, false for insertion-order
            super(maxSize + 1, 0.75f, false); // Use false for removing oldest inserted
            this.maxSize = maxSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            // Return true when map size exceeds maximum size
            return size() > maxSize;
        }
    }

    public int getMaximumEatenDishCount(int N, int[] D, int K) {
        LRUCache<Integer,Integer> cache = new LRUCache<>(K);
        int res = 0;
        for (int d:D){
            Integer put = cache.put(d, 1);
            if (put == null) {
                res++;
            }
        }
        return res;
    }

    @Test
    public void test(){
        int N = 6;
        int[] D = {1, 2, 3, 3, 2, 1};
        int K = 1;
        int res = getMaximumEatenDishCount(N,D,K);
        Assert.assertEquals(5,res);
    }

}
