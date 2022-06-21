package com.slava.primeNumbers;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
//https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/flags/
public class Flags {

    @Test
    public void test1() {
        int[] A = new int[12];
        A[0] = 1;
        A[1] = 5;
        A[2] = 3;
        A[3] = 4;
        A[4] = 3;
        A[5] = 4;
        A[6] = 1;
        A[7] = 2;
        A[8] = 3;
        A[9] = 4;
        A[10] = 6;
        A[11] = 2;
        int res = solution(A);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test2() {
        int[] A = new int[6];
        A[0] = 1;
        A[1] = 5;
        A[2] = 3;
        A[3] = 4;
        A[4] = 6;
        A[5] = 2;
        int res = solution(A);
        Assert.assertEquals(2, res);
    }

    public int solution(int[] A) {
        List<Integer> peaksIndexes = findAllPeaks(A);
        if (peaksIndexes.size() < 2) {
            return peaksIndexes.size();
        }
        int flagsMax = Integer.min(peaksIndexes.size(), (int) Math.sqrt(A.length) + 2);
        int flagsMin = 1;
        if (ifEnoughDistance(A, peaksIndexes, flagsMax)) {
            return flagsMax;
        }
        int curFlags = flagsMax;
        while ((flagsMax - flagsMin) > 1) {
            if (!ifEnoughDistance(A, peaksIndexes, curFlags)) {
                flagsMax = curFlags;
            } else {
                flagsMin = curFlags;
            }
            curFlags = (flagsMax + flagsMin) / 2;
        }
        return flagsMin;
    }

    private boolean ifEnoughDistance(int[] A, final List<Integer> peaksIndexes, int flags) {
        int index = 0;
        final ListIterator<Integer> integerListIterator = peaksIndexes.listIterator();
        int counter = 1;
        int start = integerListIterator.next();
        while (integerListIterator.hasNext()) {
            index = integerListIterator.next();
            if (index >= start + flags) {
                counter++;
                start = index;
                if (counter >= flags) {
                    return true;
                }
            }
        }
        return false;
    }

    List<Integer> findAllPeaks(int[] A) {
        List<Integer> peaksIndexes = new LinkedList<>();
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                peaksIndexes.add(i);
            }
        }
        return peaksIndexes;
    }

}
