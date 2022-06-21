package com.slava.primeNumbers;

import org.junit.Test;

import java.util.*;

public class CountSemiprimes {
    // https://app.codility.com/demo/results/trainingHBPJJH-FUW/
    @Test
    public void testSolution() {
        int length = 3;
        int max = 50000;
        int[] P = new int[length];
        int[] Q = new int[length];
        P[0] = 1;
        Q[0] = 26;
        P[1] = 4;
        Q[1] = 10;
        P[2] = 16;
        Q[2] = 20;
        solution(max, P, Q);
    }
   // https://app.codility.com/demo/results/trainingHBPJJH-FUW/
    NavigableSet<Integer> simpleNums = new TreeSet<>();
    NavigableSet<Integer> semiPrimes = new TreeSet<>();

    private void removeNonSimple(int maxNum, int simple) {
        int start = simple * simple;
        while (true) {
            if (start > maxNum) break;
            simpleNums.remove(start);
            start += simple;
        }
    }

    @Test
    public void testfillSimpleNums() {
        int max=100000;
        long start=new Date().getTime();
        fillSimpleNums(max);
        System.out.println(simpleNums.size()+"  "+(new Date().getTime()-start));
    }

    void fillSimpleNums(int maxNum) {
        simpleNums.add(2);
        for (int i = 3; i <= maxNum; i += 2) {
            simpleNums.add(i);
        }
        int simple = 3;
        while (simple <= maxNum) {
            removeNonSimple(maxNum, simple);
            SortedSet<Integer> tail = simpleNums.tailSet(simple + 1);
            if (tail.isEmpty()) {
                break;
            }
            simple = tail.first();
        }
    }

    void fillSemiSimples(int maxNum) {
        for (int simple : simpleNums) {
            for (int anotherSimple : simpleNums.tailSet(simple)) {
                final int semiPrime = simple * anotherSimple;
                if (semiPrime > maxNum) {
                    break;
                }
                semiPrimes.add(semiPrime);
            }
        }
    }

    Map<Integer, Integer> endToNum = new HashMap<>();

    int[] fillEndToNum(int nMax) {
        int[] result = new int[nMax + 1];
        if (semiPrimes.isEmpty()) {
            return result;
        }
        Iterator<Integer> iterSemi = semiPrimes.iterator();
        int semiPrime = iterSemi.next();
        int agrIndex = 0;
        for (int i = 0; i < nMax + 1; i++) {
            result[i] = agrIndex;
            if (i == semiPrime) {
                agrIndex++;
                if (iterSemi.hasNext()) {
                    semiPrime = iterSemi.next();
                }
            }
        }
        return result;
    }

    void fillEndToNum(int[] P, int[] Q) {
        NavigableSet<Integer> ends = new TreeSet<>();
        for (int i = 0; i < P.length; i++) {
            ends.add(P[i]);
            ends.add(Q[i]);
        }
        if (semiPrimes.isEmpty()) {
            for (int end : ends) {
                endToNum.put(end, 0);
            }
            return;
        }
        int i = 0;
        Iterator<Integer> iterSemi = semiPrimes.iterator();
        Integer semiPrime = iterSemi.next();
        Iterator<Integer> iterEnds = ends.iterator();
        Integer end;
        while (iterEnds.hasNext()) {
            end = iterEnds.next();
            while (end > semiPrime) {
                i++;
                if (!iterSemi.hasNext()) {
                    break;
                }
                semiPrime = iterSemi.next();
            }
            endToNum.put(end, i);
        }
    }


    void fillEndToNum1(int[] P, int[] Q) {
        NavigableSet<Integer> ends = new TreeSet<>();
        for (int i = 0; i < P.length; i++) {
            ends.add(P[i]);
            ends.add(Q[i]);
        }
        int previosNum = semiPrimes.subSet(1, true, ends.first(), false).size();
        endToNum.put(ends.first(), previosNum);
        int previousEnd = ends.first();
        ends.remove(previousEnd);
        for (int end : ends) {
            previosNum += semiPrimes.subSet(previousEnd, true, end, false).size();
            endToNum.put(end, previosNum);
            previousEnd = end;
        }
    }

    public int[] solution(int N, int[] P, int[] Q) {
        int[] result = new int[P.length];
        long start = new Date().getTime();
        fillSimpleNums(N / 2);
        long afterFillSimpleNums = new Date().getTime() - start;
        String message = System.lineSeparator() + "afterFillSimpleNums=" + afterFillSimpleNums + System.lineSeparator();
        fillSemiSimples(N);
        long afterFillSemiSimples = new Date().getTime() - start;
        message += "fillSemiSimples=" + afterFillSemiSimples + " semiSimples.size()=" + semiPrimes.size() + System.lineSeparator();
        //  fillEndToNum(P, Q);
        int[] endsArg = fillEndToNum(N);
        long afterFillEndToNum = (new Date().getTime() - start);
        message += "afterFillEndToNum=" + afterFillEndToNum + System.lineSeparator();
        for (int i = 0; i < P.length; i++) {
            //  result[i] = endToNum.get(Q[i]) - endToNum.get(P[i]);
            result[i] = endsArg[Q[i]] - endsArg[P[i]];
            result[i] += semiPrimes.contains(Q[i]) ? 1 : 0;
           /* if ((new Date().getTime() - start) > 2000) {
                message += "i=" + i + " P.length=" + P.length + System.lineSeparator();
                throw new RuntimeException(message);
            }
            */
        }
        return result;
    }

}
