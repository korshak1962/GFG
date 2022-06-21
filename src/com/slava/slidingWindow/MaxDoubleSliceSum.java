package com.slava.slidingWindow;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

//https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/
// We can use Kadane’s algorithm from two directions.
//https://app.codility.com/demo/results/training2HB6TT-5UW/
public class MaxDoubleSliceSum {

    @Test
    public void test0() {
        int[] A = new int[4];
        A[0] = 1;
        A[1] = 2;
        A[2] = 3;
        A[3] = 4;
        int res = solution(A);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test() {
        int[] A = new int[8];
        A[0] = 3;
        A[1] = 2;
        A[2] = 6;
        A[3] = -1;
        A[4] = 4;
        A[5] = 5;
        A[6] = -1;
        A[7] = 2;
        int res = solution(A);
        Assert.assertEquals(17, res);
    }

    @Test
    public void test1() {
        int[] A = new int[8];
        A[0] = 3;
        A[1] = 2;
        A[2] = 6;
        A[3] = -1000;
        A[4] = 9;
        A[5] = -1000;
        A[6] = 20;
        A[7] = 1234567;
        int res = solution(A);
        Assert.assertEquals(29, res);
    }

    @Test
    public void testPerf() {
        int length = 10000;
        int[] A = new int[length];
        for (int i = 0; i < length; i++) {
            A[i] = i + 1;
        }
        int res = solution(A);
        Assert.assertEquals(length * (length - 1) / 2 - 3, res);
    }

    @Test
    public void test11() {
        int[] A = new int[11];
        A[0] = 1;
        A[1] = -1;
        A[2] = -1;
        A[3] = 1;
        A[4] = -1;
        A[5] = -1;
        A[6] = 1;
        A[7] = 1;
        A[8] = -1;
        A[9] = 1;
        A[10] = 1;
        int res = solution(A);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test61() {
        int[] A = new int[5];
        A[0] = -1;
        A[1] = -1;
        A[2] = -1;
        A[3] = -1;
        A[4] = -1;
        int res = solution(A);
        Assert.assertEquals(0, res);
    }

    @Test
    public void test69() {
        int[] A = new int[5];
        A[0] = -1;
        A[1] = -1;
        A[2] = -1;
        A[3] = 100;
        A[4] = -1;
        int res = solution(A);
        Assert.assertEquals(100, res);
    }

    // We can use Kadane’s algorithm from two directions.
    public int solution(int[] inputArray) {
        int[] prefixMaxSum = new int[inputArray.length];
        int[] postfixMaxSum = new int[inputArray.length];
        prefixMaxSum[0] = 0;
        for (int index = 1; index < inputArray.length; index++) {
            prefixMaxSum[index] = Integer.max(prefixMaxSum[index - 1] + inputArray[index],Integer.max(0,inputArray[index]));
        }
        postfixMaxSum[inputArray.length - 1] = 0;
        for (int index = inputArray.length - 2; index > 0; index--) {
            postfixMaxSum[index] = Integer.max(postfixMaxSum[index + 1] + inputArray[index], Integer.max(0,inputArray[index]));
        }
        int maxSumOfDoubleSlice = 0;
        for (int index = 1; index < inputArray.length - 1; index++) {
            maxSumOfDoubleSlice = Integer.max(maxSumOfDoubleSlice, prefixMaxSum[index - 1] + postfixMaxSum[index + 1]);
        }
        return maxSumOfDoubleSlice;
    }

    public int solution069(int[] A) {
        int maxToReturn = 0;
        long currentSum = 0;
        long currentSumMinusMin = 0;
        int minInside = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int startIndex = 1;
        for (int i = startIndex; i < A.length - 1; i++) {
            if (A[startIndex] < 0) {
                startIndex++;
                continue;
            }
            currentSum += A[i];
            priorityQueue.add(A[i]);
            if (startIndex > 1) {
                minInside = Integer.min(0, priorityQueue.peek());
            } else {
                minInside = priorityQueue.peek();
            }
            currentSumMinusMin = currentSum - minInside;
            while ((currentSumMinusMin <= 0 || A[startIndex] <= 0) && (startIndex < i + 1) && (i > 1)) {
                currentSum -= A[startIndex];
                priorityQueue.remove(A[startIndex]);
                if (minInside == A[startIndex]) {
                    minInside = priorityQueue.peek() == null ? 0 : priorityQueue.peek();
                }
                startIndex++;
                currentSumMinusMin = currentSum - minInside;
            }
            maxToReturn = (int) Long.max(currentSumMinusMin, maxToReturn);
        }
        return maxToReturn;
    }

    public int solution69(int[] A) {
        int maxToReturn = 0;
        long currentSum = 0;
        long currentSumMinusMin = 0;
        int minInside = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int startIndex = 1;
        for (int i = startIndex; i < A.length - 1; i++) {
            if (A[startIndex] < 0) {
                startIndex++;
                continue;
            }
            currentSum += A[i];
            priorityQueue.add(A[i]);
            if (startIndex > 1) {
                minInside = Integer.min(0, priorityQueue.peek());
            } else {
                minInside = priorityQueue.peek();
            }
            currentSumMinusMin = currentSum - minInside;
            while ((currentSumMinusMin < 0 || A[startIndex] < 0) && (startIndex < i + 1)) {
                currentSum -= A[startIndex];
                priorityQueue.remove(A[startIndex]);
                if (minInside == A[startIndex]) {
                    minInside = priorityQueue.peek() == null ? 0 : priorityQueue.peek();
                }
                startIndex++;
                currentSumMinusMin = currentSum - minInside;
            }
            maxToReturn = (int) Long.max(currentSumMinusMin, maxToReturn);
        }
        return maxToReturn;
    }

    public int solution61(int[] A) {
        int maxToReturn = 0;
        long currentSum = 0;
        long currentSumMinusMin = 0;
        int minInside = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int startIndex = 1;
        for (int i = startIndex; i < A.length - 1; i++) {
            currentSum += A[i];
            priorityQueue.add(A[i]);
            minInside = priorityQueue.peek();
            currentSumMinusMin = currentSum - minInside;
            while (currentSumMinusMin < 0) {
                currentSum -= A[startIndex];
                priorityQueue.remove(A[startIndex]);
                if (minInside == A[startIndex]) {
                    minInside = priorityQueue.peek() == null ? 0 : priorityQueue.peek();
                }
                startIndex++;
                currentSumMinusMin = currentSum - minInside;
            }
            maxToReturn = (int) Long.max(currentSumMinusMin, maxToReturn);
        }
        return maxToReturn;
    }


    public int solution1(int[] A) {
        if (A.length == 3) return 0;
        int sumFromToUpInclusive[][] = fillMatrixInclusive(A);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compareTo);
        priorityQueue = new PriorityQueue<>(priorityQueue.comparator().reversed());
        PriorityQueue<Integer> priorityQueueMain = new PriorityQueue<>(priorityQueue.comparator());
        priorityQueueMain.add(0);
        int maxDown, maxUp;
        for (int i = 1; i < A.length - 1; i++) { //middle bound
            for (int k = 1; k < i; k++) {
                priorityQueue.add(sumFromToUpInclusive[k][i - 1]);
            }
            maxDown = priorityQueue.peek() == null ? 0 : priorityQueue.peek();
            priorityQueue.clear();
            for (int k = i + 1; k < A.length - 1; k++) {
                priorityQueue.add(sumFromToUpInclusive[i + 1][k]);
            }
            maxUp = priorityQueue.peek() == null ? 0 : priorityQueue.peek();
            priorityQueue.clear();
            priorityQueueMain.add(maxDown + maxUp);
        }

        return priorityQueueMain.poll();
    }

    private int[][] fillMatrixInclusive(int[] A) {
        int B[][] = new int[A.length][A.length];
        B[0][0] = A[0];
        for (int k = 1; k < A.length; k++) {
            B[0][k] = B[0][k - 1] + A[k];
        }
        for (int i = 1; i < A.length; i++) {
            for (int k = i; k < A.length; k++) {
                B[i][k] = B[i - 1][k] - A[i - 1];
            }
        }
        return B;
    }


}
