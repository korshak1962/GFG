package com.slava.primeNumbers;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
//https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/peaks/
public class Peaks {

    @Test
    public void test1() {
        int[] A = new int[12];
        A[0] = 1;
        A[1] = 2;
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
        int[] A = new int[3];
        A[0] = 1;
        A[1] = 2;
        A[2] = 3;
        int res = solution(A);
        Assert.assertEquals(0, res);
    }

    int intervalLength;
    int totalLength;

    public int solution(int[] A) {
        totalLength = A.length;
        List<StartAndLength> startToLengths = new ArrayList<>();
        int prevPeak = -1;
        // fill startToLengths with peaks values
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                startToLengths.add(new StartAndLength(prevPeak, i - prevPeak));
                prevPeak = i;
            }
        }
        if (startToLengths.isEmpty()) {
            return 0;
        }
        if (startToLengths.size() == 1) {
            return 1;
        }
        final StartAndLength lastInterval = new StartAndLength(prevPeak, totalLength - prevPeak);
        startToLengths.add(lastInterval);
        Collections.sort(startToLengths);
        intervalLength = (int) Math.ceil((double) startToLengths.get(0).length / 2);
        intervalLength = findNextIntervalLength();
        while (!isLengthEnough(startToLengths)) {
            intervalLength++;
            intervalLength = findNextIntervalLength();
        }
        return totalLength / intervalLength;
    }

    private boolean isLengthEnough(List<StartAndLength> lengthToStart) {
        for (StartAndLength startAndLength : lengthToStart) {
            if (!checkIntervalForPeak(startAndLength.start, startAndLength.length)) {
                return false;
            }
            if (startAndLength.length <= intervalLength) {
                return true;
            }
        }
        return true;
    }

    boolean checkIntervalForPeak(int start, int length) {
        int n = (int) Math.floor(start / intervalLength);
        int leftEdge = n * intervalLength;
        if (leftEdge <= start) {
            leftEdge = leftEdge + intervalLength;
        }
        final int rightEdge = leftEdge + intervalLength;
        if (rightEdge >= start + length) {
            return true;
        }
        return false;
    }

    int findNextIntervalLength() {
        while (!(totalLength % intervalLength == 0)) {
            intervalLength++;
        }
        return intervalLength;
    }

    class StartAndLength implements Comparable {
        int start;
        int length;

        public StartAndLength(int start, int length) {
            this.start = start;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof StartAndLength)) return false;
            StartAndLength that = (StartAndLength) o;
            return start == that.start &&
                    length == that.length;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, length);
        }

        @Override
        public int compareTo(Object o) {
            return ((StartAndLength) o).length - length;
        }
    }

}
