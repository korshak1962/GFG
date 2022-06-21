package com.slava.primeNumbers;

import com.slava.merging.MergeKLists;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

//https://practice.geeksforgeeks.org/problems/ugly-numbers2254/1/?problemType=functional&difficulty[]=2&page=1&sortBy=submissions&company[]=Google&query=problemTypefunctionaldifficulty[]2page1sortBysubmissionscompany[]Google
public class UglyNumbers {

    @Test
    public void test1() {
        int n = 10;
        Assert.assertEquals(12, getNthUglyNo(n));
    }

    @Test
    public void test10() {
        int n = 18;
        Assert.assertEquals(12, getNthUglyNo(n));
    }

    @Test
    public void test2() {
        int n = 42;
        Assert.assertEquals(160, getNthUglyNo(n));
    }


    static class Wrapper {
        int prime;
        long current;

        public Wrapper(int prime) {
            this.prime = prime;
            current = prime;
        }

        Long pollValue() {
            Long toReturn = current;
            current = current + prime;
            return toReturn;
        }

        Long peekValue() {
            return current;
        }

    }

    long getNthUglyNo(int n) {
        int i = 1;
        Long res = 0L;
        PriorityQueue<UglyNumbers.Wrapper> minHeap = new PriorityQueue<>((w1, w2) ->
                Long.valueOf(w1.peekValue() - w2.peekValue()).intValue());
        for (int prime : primes) {
            minHeap.add(new Wrapper(prime));
        }
        Long current = 1l;
        while (i < n) {
            Wrapper polledWrapper = minHeap.poll();
            while ((res = polledWrapper.pollValue()) == current) {
                minHeap.add(polledWrapper);
                polledWrapper = minHeap.poll();
            }
            minHeap.add(polledWrapper);
            current = res;
            System.out.println(res);
            i++;
        }
        return res;
    }

    /// wrong
    static int[] primes = {2, 3, 5};
    List<Long> uglies = new LinkedList<>();

    long getNthUglyNoWrong(int n) {
        uglies.add(1L);
        LinkedList<Long> queue = new LinkedList<>();
        long i = 1;
        queue.add(i);
        SortedSet<Long> ugliesToInsert = new TreeSet<>();
        while (uglies.size() < n) {
            int ip = 0;
            ugliesToInsert.add(queue.peekLast() * primes[0]);
            while (++ip < primes.length) {
                final long nextNextUgly = queue.peekFirst() * primes[ip];
                if (nextNextUgly <= ugliesToInsert.last()) {
                    if (!uglies.contains(nextNextUgly)) ugliesToInsert.add(nextNextUgly);
                    if (ip == primes.length - 1) {
                        queue.removeFirst();
                        ip = 0;
                    }
                } else {
                    int a = 1;
                }
            }
            uglies.addAll(ugliesToInsert);
            ugliesToInsert.clear();
            queue.add(++i);
        }
        return uglies.get(n - 1);
    }

}
